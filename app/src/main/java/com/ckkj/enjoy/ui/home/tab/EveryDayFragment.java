package com.ckkj.enjoy.ui.home.tab;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.EveryDayMainAdapter;
import com.ckkj.enjoy.adapter.NewMovieAdapter;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.ui.movie.MoiveActivity;
import com.ckkj.enjoy.ui.movie.MovieDetilsActivity;
import com.ckkj.enjoy.ui.movie.NewMoiveActivity;
import com.ckkj.enjoy.ui.movie.presenter.MoviePresenter;
import com.ckkj.enjoy.ui.movie.presenter.MoviePresenterImpl;
import com.ckkj.enjoy.ui.movie.view.MovieView;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HiWin10 on 2017/9/23 0023.
 */

public class EveryDayFragment extends BaseFragment implements EveryDayMainAdapter.onItemClickListenr, MovieView {
    @BindView(R.id.iv_content)
    IRecyclerView ivContent;

    private EveryDayMainAdapter adapter;
    private MoviePresenter presenter;
    private Context mcontext;
    private NewMovie newmovie;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_everyday;
    }

    @Override
    protected void initView() {
       initData();
    }

    private void initData() {
        mcontext=getContext();
        presenter=new MoviePresenterImpl(this);
        /*NewMovie newMovie=(NewMovie) getArguments().getSerializable("NewMovie");*/
        presenter.getNewMovie(5,0);

    }



    @Override
    public void onItemClick(int position, ImageButton imageButton) {

        switch (imageButton.getId()){
            case R.id.ib_xiandu:
                Log.d("EveryDayFragment", "闲读");
                break;
            case R.id.ib_music_hot:
                Log.d("EveryDayFragment", "音乐排行榜");
                break;
            case R.id.ib_movie_hot:
                startActivity(MoiveActivity.class);
                Log.d("EveryDayFragment", "电影排行榜");
                break;
        }
    }

    @Override
    public void onTextClick(View view) {
          switch (view.getId()){
              case R.id.more_movie:
                  startActivity(NewMoiveActivity.class);
                  break;
          }
    }

    @Override
    public void onItemMovie(ImageView v, int position, String id) {
        Intent intent = new Intent(getActivity(), MovieDetilsActivity.class);
        intent.putExtra("movie", id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(getActivity() , v, AppContent.TRANSITION_IMAGE_MOVIE);
            getActivity().startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    }

    @Override
    public void returnMusicInfo(List<Movie.SubjectsBean> movie) {

    }

    @Override
    public void returnMusicInfoDetils(MovieDetils movieDetils) {

    }

    @Override
    public void returnNewMovie(NewMovie newMovie) {
        this.newmovie=newMovie;
        initRecycle(newmovie);
    }
    private void initRecycle(NewMovie newMovie) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter=new EveryDayMainAdapter(mcontext,newMovie);
        adapter.setOnItemClickListenr(this);
        ivContent.setLayoutManager(manager);
        ivContent.setAdapter(new ScaleInAnimationAdapter(adapter));
    }
}
