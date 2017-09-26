package com.ckkj.enjoy.ui.music.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.SongListAdapter;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.bean.WrapperSongListInfo;
import com.ckkj.enjoy.ui.music.presenter.MusicPresenter;
import com.ckkj.enjoy.ui.music.view.MusicView;
import com.ckkj.enjoy.widget.LoadMoreFooterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 歌单列表
 * Created by Administrator on 2017/9/26.
 */

public class SongListFragment extends BaseFragment implements MusicView,OnLoadMoreListener {
    @BindView(R.id.ll_selector)
    LinearLayout llSelector;
    @BindView(R.id.irv_song_list)
    IRecyclerView irvSongList;
    private int pageSize =12;
    private int startPage =1;

    private SongListAdapter adapter;
    private MusicPresenter musicPresenter;
    private Context context;
    private List<WrapperSongListInfo.SongListInfo> mList = new ArrayList<>();
    private LoadMoreFooterView mFooterView;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_songlist;
    }

    @Override
    protected void initView() {
        context = getActivity();
        musicPresenter = new MusicPresenter(this);
        adapter=new SongListAdapter(context,mList);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setItemPrefetchEnabled(false);
        irvSongList.setLayoutManager(manager);
        irvSongList.setOnLoadMoreListener(this);
        irvSongList.setItemAnimator(new LandingAnimator());
        irvSongList.setIAdapter(new ScaleInAnimationAdapter(adapter));
        mFooterView = (LoadMoreFooterView) irvSongList.getLoadMoreFooterView();
        musicPresenter.requestSongListAll(AppContent.MUSIC_URL_FORMAT,AppContent.MUSIC_URL_FROM,AppContent.MUSIC_URL_METHOD_GEDAN,pageSize,startPage);
    }

    @Override
    public void returnSongListInfos(List<WrapperSongListInfo.SongListInfo> songListInfos) {
        if (songListInfos.size() != 0) {
            if (startPage == 1) {
                //第一次加载
                mList.clear();
                mList.addAll(songListInfos);
                System.out.println("初次加载数据集大小："+mList.size());
                adapter.notifyDataSetChanged();
            } else {
                //加载更多
                mList.addAll(songListInfos);
                System.out.println("数据集大小："+mList.size());
                adapter.notifyDataSetChanged();
            }
        } else {
            System.out.println("数据集中数据条目："+songListInfos.size());
            startPage--;
            mFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

    @Override
    public void onLoadMore() {
        ++startPage;
        System.out.println("当前加载的页面："+startPage);
        musicPresenter.requestSongListAll(AppContent.MUSIC_URL_FORMAT,AppContent.MUSIC_URL_FROM,AppContent.MUSIC_URL_METHOD_GEDAN,pageSize,startPage);
        mFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
    }
}
