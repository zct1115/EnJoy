package com.ckkj.enjoy.ui.music;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.LastSongPlayAdapter;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.database.music.MusicUtils;
import com.ckkj.enjoy.service.MediaPlayService;
import com.ckkj.enjoy.service.MediaServiceConnection;
import com.enjoy.entity.LastMusic;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @Override
    public void initView() {
        Intent intent = getIntent();
        toolbar.setTitle("最近播放");
        //设置toolbar 回退点击事件
        musicUtils = new MusicUtils(AppApplication.getAppContext());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setRecycle(musicUtils.getlist());

        //初始化服务连接
        mConnection = new MediaServiceConnection();
        if (mIntent == null) {
            mIntent = new Intent(this, MediaPlayService.class);
            startService(mIntent);
            bindService(mIntent, mConnection, BIND_AUTO_CREATE);
        }
    }

    private void setRecycle(List<LastMusic> data) {
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
        isPlayAll = false;
        if (mService != null) {
            mService.setPlayAll(false);
            mService.playSong(position, isLocal);
        }

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
