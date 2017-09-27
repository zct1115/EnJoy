package com.ckkj.enjoy.ui.music;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.MusicRankingListDetailAdapter;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.api.MusicApi;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseActivityWithoutStatus;
import com.ckkj.enjoy.bean.RankingListDetail;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.ui.music.presenter.RankinglistDetilsPresenter;
import com.ckkj.enjoy.ui.music.view.MusicRankingListDetailView;
import com.ckkj.enjoy.utils.ImageLoaderUtils;
import com.ckkj.enjoy.utils.StatusBarSetting;
import com.ckkj.enjoy.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zct11 on 2017/9/27.
 */

public class MusicRankingListDetailActivity extends BaseActivityWithoutStatus implements MusicRankingListDetailView , MusicRankingListDetailAdapter.onItemClickListener, MusicRankingListDetailAdapter.onPlayAllClickListener{
    @BindView(R.id.iv_album_art)
    ImageView ivAlbumArt;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_toobar)
    RelativeLayout rlToobar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.irv_song_detail)
    IRecyclerView irvSongDetail;
    @BindView(R.id.bottom_container)
    FrameLayout bottomContainer;
    private int mType;
    private List<RankingListDetail.SongListBean> mList = new ArrayList<>();
    private RankinglistDetilsPresenter presenter;
    private MusicRankingListDetailAdapter mDetailAdapter;
    private Context context;
    private String mFields;
    //请求返回的SongDetailInfo先存放在数组中，对应下标索引是其在集合中所处位置
    private SongDetailInfo[] mInfos;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rankinglist_detail;
    }

    @Override
    public void initPresenter() {
        presenter = new RankinglistDetilsPresenter(this);
    }

    @Override
    public void initView() {
        //用图片做背景
        StatusBarSetting.setTranslucent(this);
        context = this;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mType = (int) extras.get("type");
        }
        //设置标题栏
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFields = new MusicApi().encode("song_id,title,author,album_title,pic_big,pic_small,havehigh,all_rate,charge,has_mv_mobile,learn,song_source,korean_bb_song");
        setRecyclerView();
        //初始化服务连接

    }

    private void setRecyclerView() {
        mDetailAdapter = new MusicRankingListDetailAdapter(mContext, mList);
        mDetailAdapter.setOnItemClickListener(this);
        mDetailAdapter.setOnPlayAllClickListener(this);
        irvSongDetail.setLayoutManager(new LinearLayoutManager(mContext));
        irvSongDetail.setItemAnimator(new LandingAnimator());
        irvSongDetail.setIAdapter(new ScaleInAnimationAdapter(mDetailAdapter));
        irvSongDetail.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        LoadingDialog.showDialogForLoading(this).show();

        presenter.requestRankListDetail(AppContent.MUSIC_URL_FORMAT, AppContent.MUSIC_URL_FROM, AppContent.MUSIC_URL_METHOD_RANKING_DETAIL, mType, 0, 100, mFields);
    }

    @Override
    public void returnSongDetail(SongDetailInfo info) {

    }

    @Override
    public void returnRankingListDetail(RankingListDetail detail) {
        List<RankingListDetail.SongListBean> list = detail.getSong_list();
        setUI(detail.getBillboard());
        mList.addAll(list);
        //初始化数组集合
        mInfos = new SongDetailInfo[mList.size()];
        mDetailAdapter.notifyDataSetChanged();
        initMusicList();
    }

    private void initMusicList() {

    }

    private void setUI(RankingListDetail.BillboardBean billboard) {
        tvName.setText(billboard.getName());
        ImageLoaderUtils.display(this, ivAlbumArt, billboard.getPic_s260());
        LoadingDialog.cancelDialogForLoading();
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemClick() {

    }
}
