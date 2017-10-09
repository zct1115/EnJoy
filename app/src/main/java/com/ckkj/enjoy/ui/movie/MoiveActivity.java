package com.ckkj.enjoy.ui.movie;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.MovieAdapter;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.ui.movie.presenter.MoviePresenterImpl;
import com.ckkj.enjoy.ui.movie.view.MovieView;
import com.ckkj.enjoy.utils.CheckNetwork;
import com.ckkj.enjoy.widget.LoadMoreFooterView;
import com.ckkj.enjoy.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 显示电影列表页面
 */
public class MoiveActivity extends BaseActivity implements MovieView,MovieAdapter.onItemClickListenr,OnLoadMoreListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycle)
    IRecyclerView recycle;

    private MovieAdapter adapter;
    private MoviePresenterImpl moviePresenter;
    private List<Movie.SubjectsBean> movies = new ArrayList<Movie.SubjectsBean>();
    private int count=20;
    private int start=0;
    private LoadMoreFooterView mFooterView;

    /**
     * 实例化P
     */
    @Override
    public void initPresenter() {
        moviePresenter = new MoviePresenterImpl(this);
    }

    /**
     * 初始化视图
     */
    @Override
    public void initView() {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("电影");
        LoadingDialog.showDialogForLoading(this);

        initData(movies);
        /*回退键触发事件*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData(final List<Movie.SubjectsBean> movies) {
        /*初始化适配器*/
        adapter = new MovieAdapter(this, movies);
        /*设置GirdLayoutManager布局，两列，垂直方向*/
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        /*设置布局到RecycleView*/
        recycle.setLayoutManager(manager);
        /*设置RecycleView刷新*/
        recycle.setOnLoadMoreListener(this);
        //recycle.setOnRefreshListener(this);
        /*设置RecycleView点击事件*/
        adapter.setOnItemClickListenr(this);
        /*设置RecycleView每一项动画效果*/
        recycle.setItemAnimator(new LandingAnimator());
        /*设置适配器*/
        recycle.setIAdapter(new ScaleInAnimationAdapter(adapter));
        mFooterView=(LoadMoreFooterView) recycle.getLoadMoreFooterView();
        moviePresenter.requestHotMovie(count,start);
    }

    /**
     * MVP返回的数据进行处理
     * @param movie
     */
    @Override
    public void returnMusicInfo(List<Movie.SubjectsBean> movie) {

       /* 初始化数据*/
        if(movie!=null){
            if(start==0){
                movies.clear();
                movies.addAll(movie);
                adapter.notifyDataSetChanged();
            }else {
                if(movie.size()<count){
                  count= movie.size();
                }
                movies.addAll(movie);
                adapter.notifyDataSetChanged();
            }
        }else {
            start--;
            mFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
        }
        LoadingDialog.cancelDialogForLoading();

    }

    @Override
    public void returnMusicInfoDetils(MovieDetils movieDetils) {}

    @Override
    public void returnNewMovie(List<NewMovie.SubjectsBean> newMovie) {

    }


    /**
     * 获取布局ID
     * @return
     */
    @Override
    public int getLayoutID() {
        return R.layout.activity_moive;
    }

    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MoiveActivity.class);
        activity.startActivity(intent);
    }


    /**
     * RecycleView每一项点击事件
     * @param position
     * @param imageView
     */
    @Override
    public void onItemClick(int position, ImageView imageView) {
        Intent intent = new Intent(this, MovieDetilsActivity.class);
        intent.putExtra("movie", movies.get(position).getId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation((Activity) mContext, imageView, AppContent.TRANSITION_IMAGE_MOVIE);
            mContext.startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(imageView, imageView.getWidth() / 2, imageView.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());
        }
    }

    @Override
    public void onLoadMore() {

        if(count<20){
            //moviePresenter.requestHotMovie(count,start);
            mFooterView.setStatus(LoadMoreFooterView.Status.THE_END);

        }else {
            start=start+count;
            moviePresenter.requestHotMovie(count,start);
            mFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
        }

    }
}
