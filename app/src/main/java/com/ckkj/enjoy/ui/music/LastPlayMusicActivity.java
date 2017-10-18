package com.ckkj.enjoy.ui.music;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.LastSongPlayAdapter;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.Song;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.database.music.MusicUtils;
import com.ckkj.enjoy.service.MediaPlayService;
import com.ckkj.enjoy.service.MediaServiceConnection;
import com.enjoy.entity.LastMusic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 最近播放音乐，通过本地存储播放的历史实现
 */
public class LastPlayMusicActivity extends BaseActivity implements LastSongPlayAdapter.onItemClickListenr {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycle)
    IRecyclerView recycle;
    //最近音乐播放适配器
    private LastSongPlayAdapter adapter;
    //数据库处理工具类
    private MusicUtils musicUtils;
    private MediaPlayService mService;
    private MediaServiceConnection mConnection;
    private Intent mIntent;
    private static MediaPlayService.MediaBinder mMediaBinder;
    private boolean isPlayAll = false;
    private boolean isLocal;
    private HashMap<String, Integer> positionMap = new HashMap<>();
    //指示现在加入musicList集合中的元素下标应该是多少
    private AtomicInteger index = new AtomicInteger(0);
    private LastMusic[] mInfos;

    private AlertDialog mDialog;
    private List<LastMusic> data=new ArrayList<>();

    @Override
    public void initView() {
        Intent intent = getIntent();
        toolbar.setTitle("最近播放");
        toolbar.setTitleTextColor(Color.WHITE);
        //设置toolbar 回退点击事件
        musicUtils = new MusicUtils(AppApplication.getAppContext());
        data=musicUtils.getlist();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setRecycle();

        //初始化服务连接
        mConnection = new MediaServiceConnection();
        if (mIntent == null) {
            mIntent = new Intent(this, MediaPlayService.class);
            startService(mIntent);
            bindService(mIntent, mConnection, BIND_AUTO_CREATE);
        }
    }

    private void setRecycle() {
        if (data != null) {
            adapter = new LastSongPlayAdapter(this, data);
            GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
            /*设置布局到RecycleView*/
            recycle.setLayoutManager(manager);
            //适配器监听事件
            adapter.setOnItemClickListenr(this);
            /*设置RecycleView每一项动画效果*/
            recycle.setItemAnimator(new LandingAnimator());
            /*设置适配器*/
            recycle.setIAdapter(new ScaleInAnimationAdapter(adapter));
        } else {
            // TODO: 2017/10/16 待增加空布局
        }


    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_last_play_music;
    }


    @Override
    public void onItemClick(int position, ImageView imageView) {
        mInfos=new LastMusic[data.size()];
        for(int i=0;i<data.size();i++){
            String song_id=data.get(i).getSong_id();
            positionMap.put(song_id,i);
        }
        // TODO: 2017/10/18 此处有问题 
       for(int j=0;j<data.size();j++){
           if (mMediaBinder != null) {
               if (mService == null) {
                   mService = mMediaBinder.getMediaPlayService();
               }

               if (data == null) {
                   // TODO: 2017/10/10 为空 不能播放 后续需要处理
               } else {
                   String song_id=data.get(position).getSong_id();
                   Integer positionid = positionMap.get(song_id);
                   mInfos[positionid]=data.get(position);
               }
               int currentNumber = index.addAndGet(1);
               if (currentNumber == mInfos.length) {
                   for (int i = 0; i < mInfos.length; i++) {
                       if (i == 0) {
                           //先清除之前的播放集合
                           mService.clearlastMusicList();
                       }
                       mService.addlastMusicList(mInfos[i]);
                   }

               }
           }
       }
        isPlayAll = false;
        if (mService != null) {
            mService.setPlayAll(false);
            mService.playlastSong(position, isLocal);
        }

    }


    @Override
    public void ondeleteItem(final int position) {
        final LastMusic lastMusic=new LastMusic();
        lastMusic.setId(musicUtils.getlist().get(position).getId());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定删除"+musicUtils.getlist().get(position).getTitle()+"?");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialog.dismiss();
            }
        });
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                musicUtils.deleteitemMusic(lastMusic);
                mDialog.dismiss();
                EventBus.getDefault().post(new Song());
                data.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        mDialog = builder.create();
        mDialog.show();


    }

    private static class MediaServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMediaBinder = (MediaPlayService.MediaBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
