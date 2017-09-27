package com.ckkj.enjoy.ui.music.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.IRecyclerView;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.MusicRankingAdapter;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.bean.RankingListItem;
import com.ckkj.enjoy.ui.music.presenter.RankinglistPresenter;
import com.ckkj.enjoy.ui.music.view.RankinglistView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 排行榜
 * Created by Administrator on 2017/9/26.
 */

public class RankingFragment extends BaseFragment implements RankinglistView {
    @BindView(R.id.ix_music)
    IRecyclerView mIrvRanking;

    private List<RankingListItem.RangkingDetail> list=new ArrayList<>();
    private RankinglistPresenter presenter;
    private Context context;
    private MusicRankingAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.ranking_music_fragment;
    }

    @Override
    protected void initView() {
        context=getActivity();
        presenter=new RankinglistPresenter(this);
        mAdapter = new MusicRankingAdapter(context, list);
        mIrvRanking.setLayoutManager(new LinearLayoutManager(context));
        mIrvRanking.setItemAnimator(new LandingAnimator());
        mIrvRanking.setIAdapter(new ScaleInAnimationAdapter(mAdapter));
        mIrvRanking.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        presenter.requestRankinglist(AppContent.MUSIC_URL_FORMAT,AppContent.MUSIC_URL_FROM,AppContent.MUSIC_URL_METHOD_RANKINGLIST,AppContent.MUSIC_URL_RANKINGLIST_FLAG);
    }

    @Override
    public void returnRankingListInfos(List<RankingListItem.RangkingDetail> rangkingListInfos) {
      list.addAll(rangkingListInfos);
        mAdapter.notifyDataSetChanged();
    }
}
