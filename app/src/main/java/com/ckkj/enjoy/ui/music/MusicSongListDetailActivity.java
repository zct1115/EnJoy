package com.ckkj.enjoy.ui.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.MusicSongListDetailAdapter;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.base.BaseActivityWithoutStatus;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.bean.SongListDetail;
import com.ckkj.enjoy.service.MediaPlayService;
import com.ckkj.enjoy.service.MediaServiceConnection;
import com.ckkj.enjoy.ui.music.presenter.MusicSongListDetailPresenterImpl;
import com.ckkj.enjoy.ui.music.view.MusicSongListDetailView;
import com.ckkj.enjoy.utils.ImageLoaderUtils;
import com.ckkj.enjoy.utils.StatusBarSetting;
import com.ckkj.enjoy.widget.LoadingDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zct11 on 2017/9/28.
 */

public class MusicSongListDetailActivity extends BaseActivityWithoutStatus implements MusicSongListDetailView, MusicSongListDetailAdapter.onItemClickListener, MusicSongListDetailAdapter.onPlayAllClickListener{
    @BindView(R.id.album_art)
    ImageView albumArt;
    @BindView(R.id.overlay)
    View overlay;
    @BindView(R.id.iv_songlist_photo)
    ImageView ivSonglistPhoto;
    @BindView(R.id.tv_songlist_count)
    TextView tvSonglistCount;
    @BindView(R.id.fra)
    FrameLayout fra;
    @BindView(R.id.tv_songlist_name)
    TextView tvSonglistName;
    @BindView(R.id.tv_songlist_detail)
    TextView tvSonglistDetail;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.iv_comment)
    ImageView ivComment;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_download)
    LinearLayout llDownload;
    @BindView(R.id.headerdetail)
    RelativeLayout headerdetail;
    @BindView(R.id.headerview)
    FrameLayout headerview;
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


    private String songListid;
    private boolean isLocal;
    private String photoUrl;
    private String listName;
    private String detail;
    private String count;
    private static Context mContext;
    private static int radius = 25;
    private MusicSongListDetailPresenterImpl mPresenter;
    private MusicSongListDetailAdapter mDetailAdapter;
    private List<SongListDetail.SongDetail> mList = new ArrayList<>();
    private Intent mIntent;
    private static MediaPlayService.MediaBinder mMediaBinder;
    private List<SongListDetail.SongDetail> mMReturnList;
    private MediaPlayService mService;
    private boolean isPlayAll = false;
    private MediaServiceConnection mConnection;
    //song_id 对应的在集合中的位置
    private HashMap<String, Integer> positionMap = new HashMap<>();
    //请求返回的SongDetailInfo先存放在数组中，对应下标索引是其在集合中所处位置
    private SongDetailInfo[] mInfos;
    //指示现在加入musicList集合中的元素下标应该是多少
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public void initView() {
        StatusBarSetting.setTranslucent(this);
        mContext=this;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            songListid = (String) extras.get("songListId");
            isLocal = (boolean) extras.get("islocal");
            photoUrl = (String) extras.get("songListPhoto");
            listName = (String) extras.get("songListname");
            detail = (String) extras.get("songListTag");
            count = (String) extras.get("songListCount");
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setUI();
        setRecyclerView();
        mDetailAdapter.setOnItemClickListener(this);
        mDetailAdapter.setOnPlayAllClickListener(this);

    }

    private void initMusicList() {
        for (int i = 0; i < mList.size(); i++) {
            SongListDetail.SongDetail songDetail = mList.get(i);
            String song_id = songDetail.getSong_id();
            positionMap.put(song_id, i);
            mPresenter.requestSongDetail(AppContent.MUSIC_URL_FROM_2, AppContent.MUSIC_URL_VERSION, AppContent.MUSIC_URL_FORMAT, AppContent.MUSIC_URL_METHOD_SONG_DETAIL
                    , song_id);
        }
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

    @Override
    public void onItemClick(List<SongListDetail.SongDetail> songDetails) {
        //播放全部
        isPlayAll = true;
        if (mService != null) {
            mService.playAll(isLocal);
        }


    }

    @Override
    public void returnSongListDetailInfos(List<SongListDetail.SongDetail> songDetails) {
        mList.addAll(songDetails);
        //初始化数组集合
        mInfos = new SongDetailInfo[mList.size()];
        mDetailAdapter.notifyDataSetChanged();
        initMusicList();
    }

    @Override
    public void returnSongDetail(SongDetailInfo info) {
        if (mMediaBinder != null) {
            if (mService == null) {
                mService = mMediaBinder.getMediaPlayService();
            }

            if(info.getSonginfo()==null){
                // TODO: 2017/5/10 为空 不能播放 后续需要处理
            }else{
                String song_id = info.getSonginfo().getSong_id();
                Integer position = positionMap.get(song_id);
                mInfos[position] = info;
            }
            int currentNumber = index.addAndGet(1);
            if (currentNumber == mInfos.length) {
                for (int i = 0; i < mInfos.length; i++) {
                    if(i==0){
                        //先清除之前的播放集合
                        mService.clearMusicList();
                    }
                    mService.addMusicList(mInfos[i]);
                }
                LoadingDialog.cancelDialogForLoading();
            }


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

    private void setRecyclerView() {
        mDetailAdapter = new MusicSongListDetailAdapter(mContext, mList);
        irvSongDetail.setLayoutManager(new LinearLayoutManager(mContext));
        irvSongDetail.setItemAnimator(new LandingAnimator());
        irvSongDetail.setIAdapter(new ScaleInAnimationAdapter(mDetailAdapter));
        irvSongDetail.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        LoadingDialog.showDialogForLoading(this).show();
        mPresenter.requestSongListDetail(AppContent.MUSIC_URL_FORMAT, AppContent.MUSIC_URL_FROM, AppContent.MUSIC_URL_METHOD_SONGLIST_DETAIL, songListid);
    }

    private void setUI() {
        ImageLoaderUtils.display(this, ivSonglistPhoto, photoUrl);
        tvSonglistCount.setText(count);
        tvSonglistName.setText(listName);
        String[] split = detail.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("标签：");
        for (int i = 0; i < split.length; i++) {
            stringBuffer.append(split[i] + " ");
        }
        tvSonglistDetail.setText(stringBuffer);
        new PathAsyncTask(albumArt).execute(photoUrl);
        mConnection = new MediaServiceConnection();
        if (mIntent == null) {
            mIntent = new Intent(this, MediaPlayService.class);
            startService(mIntent);
            bindService(mIntent, mConnection, BIND_AUTO_CREATE);
        }
    }

    private static class PathAsyncTask extends AsyncTask<String, Void, String> {
        private ImageView mImageView;

        public PathAsyncTask(ImageView view) {
            this.mImageView = view;
        }

        private String mPath;
        private FileInputStream mIs;

        @Override
        protected String doInBackground(String... params) {
            FutureTarget<File> future = Glide.with(AppApplication.getAppContext())
                    .load(params[0])
                    .downloadOnly(100, 100);
            try {
                File cacheFile = future.get();
                mPath = cacheFile.getAbsolutePath();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return mPath;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                mIs = new FileInputStream(s);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(mIs);
            applyBlur(bitmap, mImageView);

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_songlist_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter = new MusicSongListDetailPresenterImpl(this, this);
    }



    private static void applyBlur(Bitmap bgBitmap, ImageView view) {
        //处理得到模糊效果的图
        RenderScript renderScript = RenderScript.create(AppApplication.getAppContext());
        // Allocate memory for Renderscript to work with
        final Allocation input = Allocation.createFromBitmap(renderScript, bgBitmap);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());
        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);
        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);
        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);
        // Copy the output to the blurred bitmap
        output.copyTo(bgBitmap);
        renderScript.destroy();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), bgBitmap);
        view.setBackground(bitmapDrawable);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

}
