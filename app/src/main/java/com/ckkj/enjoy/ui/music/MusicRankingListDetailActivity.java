package com.ckkj.enjoy.ui.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.ckkj.enjoy.service.MediaPlayService;
import com.ckkj.enjoy.ui.music.presenter.RankinglistDetilsPresenter;
import com.ckkj.enjoy.ui.music.view.MusicRankingListDetailView;
import com.ckkj.enjoy.utils.ImageLoaderUtils;
import com.ckkj.enjoy.utils.StatusBarSetting;
import com.ckkj.enjoy.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    //song_id 对应的在集合中的位置
    private HashMap<String, Integer> positionMap = new HashMap<>();
    private static MediaPlayService.MediaBinder mMediaBinder;
    private MediaPlayService mService;
    private MediaServiceConnection mConnection;
    //指示现在加入musicList集合中的元素下标应该是多少
    private AtomicInteger index = new AtomicInteger(0);
    private Intent mIntent;
    private boolean isPlayAll = false;
    private boolean isLocal;

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
        mConnection = new MediaServiceConnection();
        if (mIntent == null) {
            mIntent = new Intent(this, MediaPlayService.class);
            startService(mIntent);
            bindService(mIntent, mConnection, BIND_AUTO_CREATE);
        }
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
    public void returnRankingListDetail(RankingListDetail detail) {
        List<RankingListDetail.SongListBean> list = detail.getSong_list();
        setUI(detail.getBillboard());
        mList.addAll(list);
        //初始化数组集合
        mInfos = new SongDetailInfo[mList.size()];
        mDetailAdapter.notifyDataSetChanged();
        initMusicList();
    }
    @Override
    public void returnSongDetail(SongDetailInfo info) {
        System.out.println("回调次数：" + index);
        if (mMediaBinder != null) {
            if (mService == null) {
                mService = mMediaBinder.getMediaPlayService();
            }

            if (info.getSonginfo() == null) {

                // TODO: 2017/5/10 为空 不能播放 后续需要处理
            } else {
                Log.d("MusicRankingListDetailA", "info.getSonginfo():" + info.getSonginfo());
                String song_id = info.getSonginfo().getSong_id();
                Integer position = positionMap.get(song_id);
                mInfos[position] = info;
            }
            int currentNumber = index.addAndGet(1);
            if (currentNumber == mInfos.length) {
                for (int i = 0; i < mInfos.length; i++) {
                    if (i == 0) {
                        //先清除之前的播放集合
                        mService.clearMusicList();
                    }
                    mService.addMusicList(mInfos[i]);
                }

            }
            LoadingDialog.cancelDialogForLoading();

        }
    }


    private void initMusicList() {
        for (int i = 0; i < mList.size(); i++) {
            RankingListDetail.SongListBean songDetail = mList.get(i);
            String song_id = songDetail.getSong_id();
            positionMap.put(song_id, i);
            presenter.requestSongDetail(AppContent.MUSIC_URL_FROM_2, AppContent.MUSIC_URL_VERSION, AppContent.MUSIC_URL_FORMAT, AppContent.MUSIC_URL_METHOD_SONG_DETAIL
                    , song_id);
        }
    }

    private void setUI(RankingListDetail.BillboardBean billboard) {
        tvName.setText(billboard.getName());
        ImageLoaderUtils.display(this, ivAlbumArt, billboard.getPic_s260());

    }


    @Override
    public void onItemClick(int position) {
        //播放单个
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
    public void onItemClick() {
        //播放全部
        isPlayAll = true;
        if (mService != null) {
            mService.playAll(isLocal);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
