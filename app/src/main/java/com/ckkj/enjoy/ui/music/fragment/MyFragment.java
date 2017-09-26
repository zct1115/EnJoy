package com.ckkj.enjoy.ui.music.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.MusicMyAdapter;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.bean.MusicMyItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ting on 2017/9/25.
 */

public class MyFragment extends BaseFragment {


    @BindView(R.id.irv_music_my)
    IRecyclerView irvMusicMy;
    @BindView(R.id.iv_broadcast)
    ImageView ivBroadcast;
    private List<MusicMyItem> mList;
    private Context mcontext;
    private MusicMyAdapter mMusicMyAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_music_my;
    }

    @Override
    protected void initView() {
        mcontext = getActivity();
        initData();
        setRecycle();
    }

    private void setRecycle() {
        mMusicMyAdapter = new MusicMyAdapter(mcontext, mList);
        irvMusicMy.setLayoutManager(new LinearLayoutManager(mcontext));
        // TODO: 2017/4/26  先默认不能刷新
//        mIrvMusicMy.setOnRefreshListener(this);
        irvMusicMy.setItemAnimator(new DefaultItemAnimator());
        irvMusicMy.setIAdapter(mMusicMyAdapter);
        irvMusicMy.addItemDecoration(new DividerItemDecoration(mcontext, DividerItemDecoration.VERTICAL));

    }

    private void initData() {
        // TODO: 2017/9/25 待解决
        mList = new ArrayList<>();
        MusicMyItem item1 = new MusicMyItem();
        item1.setTitle("本地音乐");
        item1.setCount(0);
        item1.setImageRes(getResources().getDrawable(R.drawable.music_local));
        mList.add(item1);
        MusicMyItem item2 = new MusicMyItem();
        item2.setTitle("最近播放");
        item2.setCount(0);
        item2.setImageRes(getResources().getDrawable(R.drawable.music_recent));
        mList.add(item2);
        MusicMyItem item3 = new MusicMyItem();
        item3.setTitle("下载管理");
        item3.setCount(0);
        item3.setImageRes(getResources().getDrawable(R.drawable.music_download));
        mList.add(item3);
        MusicMyItem item4 = new MusicMyItem();
        item4.setTitle("我的歌手");
        item4.setCount(0);
        item4.setImageRes(getResources().getDrawable(R.drawable.music_singer));
        mList.add(item4);
        MusicMyItem item5 = new MusicMyItem();
        item5.setTitle("我喜欢的音乐");
        item5.setCount(0);
        item5.setImageRes(getResources().getDrawable(R.drawable.music_list_default));
        mList.add(item5);
    }


}
