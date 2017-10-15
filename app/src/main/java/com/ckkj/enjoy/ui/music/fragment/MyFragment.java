package com.ckkj.enjoy.ui.music.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.MusicMyAdapter;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.bean.MusicMyItem;
import com.ckkj.enjoy.bean.Song;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.database.music.MusicUtils;
import com.ckkj.enjoy.message.Download;
import com.ckkj.enjoy.ui.MainActivity;
import com.ckkj.enjoy.ui.home.MyInformationActivity;
import com.ckkj.enjoy.ui.music.DownloadManagerActivity;
import com.ckkj.enjoy.ui.music.LastPlayMusicActivity;
import com.ckkj.enjoy.utils.SPUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by Ting on 2017/9/25.
 */

public class MyFragment extends BaseFragment implements MusicMyAdapter.onItemClickListenr{


    @BindView(R.id.irv_music_my)
    IRecyclerView irvMusicMy;
    @BindView(R.id.iv_broadcast)
    ImageView ivBroadcast;
    private List<MusicMyItem> mList;
    private Context mcontext;
    private MusicMyAdapter mMusicMyAdapter;
    private int count=0;
    private MusicUtils utils;
    private AlertDialog mDialog;

    private ArrayList<SongDetailInfo.SonginfoBean> list=new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_music_my;
    }

    @Override
    protected void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mcontext = getActivity();
        initData();
        setRecycle();

    }

    private void setRecycle() {
        mMusicMyAdapter = new MusicMyAdapter(mcontext, mList);
        irvMusicMy.setLayoutManager(new LinearLayoutManager(mcontext));
        // TODO: 2017/4/26  先默认不能刷新
        //irvMusicMy.setOnRefreshListener(this);
        mMusicMyAdapter.setOnItemClickListener(this);
        irvMusicMy.setItemAnimator(new DefaultItemAnimator());
        irvMusicMy.setIAdapter(mMusicMyAdapter);
        irvMusicMy.addItemDecoration(new DividerItemDecoration(mcontext, DividerItemDecoration.VERTICAL));

    }

    private void initData() {

        utils=new MusicUtils(AppApplication.getAppContext());
        Log.d("MyFragment", "utils.getlist():" + utils.getlist());
        mList = new ArrayList<>();
        MusicMyItem item1 = new MusicMyItem();
        item1.setTitle("本地音乐");
        item1.setCount(0);
        item1.setImageRes(getResources().getDrawable(R.drawable.music_local));
        mList.add(item1);
        MusicMyItem item2 = new MusicMyItem();
        item2.setTitle("最近播放");
        item2.setCount((int)utils.musicCount());
        item2.setImageRes(getResources().getDrawable(R.drawable.music_recent));
        mList.add(item2);
        MusicMyItem item3 = new MusicMyItem();
        item3.setTitle("下载管理");
        item3.setCount(0);
        item3.setImageRes(getResources().getDrawable(R.drawable.music_download));
        mList.add(item3);
        MusicMyItem item4 = new MusicMyItem();
        item4.setTitle("我的收藏");
        item4.setCount(0);
        item4.setImageRes(getResources().getDrawable(R.drawable.music_singer));
        mList.add(item4);

    }


    @Override
    public void onItemClick(int position) {
         switch (position){
             case 0:
                 /*本地音乐*/
                 break;
             case 1:
                 /*最近播放*/
                Intent intent= new Intent(mcontext,LastPlayMusicActivity.class);
                 intent.putExtra("song", (Serializable) list);
                 startActivity(intent);
                 break;
             case 2:
                 startActivity(new Intent(mcontext, DownloadManagerActivity.class));
                 /*下载管理*/
                 break;
             case 3:
                 /*我的收藏*/
                 if(SPUtils.getSharedBooleanData(AppApplication.getAppContext(),"islogin")){

                 }else{
                     //提示还未登录
                     AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                     builder.setTitle("提示");
                     builder.setMessage("还未登录！\n\n登录后才能查看我的收藏！");
                     builder.setPositiveButton("知道", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             mDialog.dismiss();
                         }
                     });
                     mDialog = builder.create();
                     mDialog.show();
                 }
                 break;
         }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getLastMusic(Song info){


        for(int i=0;i<mList.size();i++){
            if(i==1){
                mList.get(i).setCount((int) utils.musicCount());
            }
        }
        mMusicMyAdapter.notifyDataSetChanged();

    }


}
