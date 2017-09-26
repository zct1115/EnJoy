package com.ckkj.enjoy.ui.movie;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.MovieAdapter;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.client.RxDisposeManager;
import com.ckkj.enjoy.ui.movie.presenter.MoviePresenterImpl;
import com.ckkj.enjoy.ui.movie.view.MovieView;
import com.ckkj.enjoy.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by HiWin10 on 2017/9/22 0022.
 */

public class Movie extends BaseFragment implements MovieView ,MovieAdapter.onItemClickListenr{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycle)
    RecyclerView recycle;

    private MovieAdapter adapter;
    private Context mcontext;
    private MoviePresenterImpl moviePresenter;
    private List<com.ckkj.enjoy.bean.Movie.SubjectsBean> movies = new ArrayList<com.ckkj.enjoy.bean.Movie.SubjectsBean>();


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_moive;
    }

    @Override
    protected void initView() {
        mcontext = getActivity();
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("电影");
        moviePresenter.requestHotMovie();
        /*回退键触发事件*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void returnMusicInfo(List<com.ckkj.enjoy.bean.Movie.SubjectsBean> movie) {
        /*初始化适配器*/
        adapter = new MovieAdapter(mcontext, movies);
        /*设置GirdLayoutManager布局，两列，垂直方向*/
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        /*设置布局到RecycleView*/
        recycle.setLayoutManager(manager);
        /*设置RecycleView刷新*/
        //recycle.setOnRefreshListener(this);
        /*设置RecycleView点击事件*/
        adapter.setOnItemClickListenr(this);
        /*设置RecycleView每一项动画效果*/
        recycle.setItemAnimator(new LandingAnimator());
        /*设置适配器*/
        recycle.setAdapter(adapter);
    }

    @Override
    public void returnMusicInfoDetils(MovieDetils movieDetils) {
    }

    @Override
    public void returnNewMovie(NewMovie newMovie) {

    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            //可见，并且是第一次加载
            moviePresenter.requestHotMovie();
            LoadingDialog.showDialogForLoading(getActivity());
        } else {
            LoadingDialog.cancelDialogForLoading();
        }
    }

    @Override
    public void onItemClick(int position, ImageView imageView) {

    }
}
