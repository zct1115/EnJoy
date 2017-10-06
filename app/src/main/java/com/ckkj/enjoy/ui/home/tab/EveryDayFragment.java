package com.ckkj.enjoy.ui.home.tab;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.EveryDayMainAdapter;
import com.ckkj.enjoy.adapter.SongListAdapter;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.bean.OtherMovie;
import com.ckkj.enjoy.bean.WrapperSongListInfo;
import com.ckkj.enjoy.ui.home.presenter.Imp.OtherMoviePresenterImp;
import com.ckkj.enjoy.ui.home.view.OtherMovieView;
import com.ckkj.enjoy.ui.movie.MoiveActivity;
import com.ckkj.enjoy.ui.movie.MovieDetilsActivity;
import com.ckkj.enjoy.ui.movie.NewMoiveActivity;
import com.ckkj.enjoy.ui.music.MusicRankingListDetailActivity;
import com.ckkj.enjoy.ui.music.presenter.MusicPresenter;
import com.ckkj.enjoy.ui.music.view.MusicView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HiWin10 on 2017/9/23 0023.
 */

public class EveryDayFragment extends BaseFragment implements EveryDayMainAdapter.onItemClickListenr, OtherMovieView, MusicView,OnLoadMoreListener {
    @BindView(R.id.iv_content)
    IRecyclerView ivContent;
    @BindView(R.id.iv_content_music)
    IRecyclerView ivContentMusic;


    private EveryDayMainAdapter adapter;
    private OtherMoviePresenterImp presenter;
    private MusicPresenter musicPresenter;
    private SongListAdapter songadapter;
    private List<WrapperSongListInfo.SongListInfo> mList = new ArrayList<>();
    private Context mcontext;
    private List<OtherMovie.SubjectsBean> data = new ArrayList<>();
    private OtherMovie other = new OtherMovie();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_everyday;
    }

    @Override
    protected void initView() {
        initData();
    }

    private void initData() {
        mcontext = getContext();
        presenter = new OtherMoviePresenterImp(this);
        /*NewMovie newMovie=(NewMovie) getArguments().getSerializable("NewMovie");*/
        presenter.getOtherMovie();
        initRecycle();
        initMusic();

    }

    private void initMusic() {
        musicPresenter = new MusicPresenter(this);
        musicPresenter.requestSongListAll(AppContent.MUSIC_URL_FORMAT,AppContent.MUSIC_URL_FROM,AppContent.MUSIC_URL_METHOD_GEDAN,6,1);
        songadapter=new SongListAdapter(mcontext,mList);
        StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager1.setItemPrefetchEnabled(false);
        ivContentMusic.setLayoutManager(manager1);
        ivContentMusic.setItemAnimator(new LandingAnimator());
        ivContentMusic.setIAdapter(new ScaleInAnimationAdapter(songadapter));

    }



    @Override
    public void onItemClick(int position, ImageButton imageButton) {

        switch (imageButton.getId()) {
            case R.id.ib_xiandu:
                Log.d("EveryDayFragment", "闲读");
                break;
            case R.id.ib_music_hot:
                Log.d("EveryDayFragment", "音乐排行榜");
                Intent intent = new Intent(mcontext, MusicRankingListDetailActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.ib_movie_hot:
                startActivity(MoiveActivity.class);
                Log.d("EveryDayFragment", "电影排行榜");
                break;
        }
    }

    @Override
    public void onTextClick(View view) {
        switch (view.getId()) {
            case R.id.more_movie:
                Intent intent = new Intent(getActivity(), NewMoiveActivity.class);
                intent.putExtra("OtherMovie", other);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemMovie(ImageView v, int position, String id) {
        Intent intent = new Intent(getActivity(), MovieDetilsActivity.class);
        intent.putExtra("movie", id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(getActivity(), v, AppContent.TRANSITION_IMAGE_MOVIE);
            getActivity().startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    }


    private void initRecycle() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new EveryDayMainAdapter(mcontext, data);
        adapter.setOnItemClickListenr(this);
        ivContent.setLayoutManager(manager);
        ivContent.setAdapter(new ScaleInAnimationAdapter(adapter));
    }

    @Override
    public void getOtherMovieView(OtherMovie otherMovie) {
        if (otherMovie != null) {
            other = otherMovie;
            data.addAll(otherMovie.getSubjects());
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onLoadMore() {

    }

    @Override
    public void returnSongListInfos(List<WrapperSongListInfo.SongListInfo> songListInfos) {
        if (songListInfos.size() != 0) {
            mList.addAll(songListInfos);
            songadapter.notifyDataSetChanged();
        }
    }
}
