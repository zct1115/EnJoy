package com.ckkj.enjoy.ui.movie;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.MovieDetailAdapter;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.ui.movie.presenter.MoviePresenterImpl;
import com.ckkj.enjoy.ui.movie.view.MovieView;
import com.ckkj.enjoy.utils.ImageLoaderUtils;
import com.ckkj.enjoy.widget.LoadingDialog;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetilsActivity extends BaseActivity implements MovieView,MovieDetailAdapter.OnItemClickListener {


    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.dierct)
    TextView dierct;
    @BindView(R.id.act)
    TextView act;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.county)
    TextView county;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.xrv_cast)
    XRecyclerView xrvCast;
    @BindView(R.id.content)
    TextView content;
    private MoviePresenterImpl presenter;
    private MovieDetils movie_detils;

    @Override
    public void initView() {

        Intent intent = getIntent();
        String movie = intent.getStringExtra("movie");
        LoadingDialog.showDialogForLoading(this);
        presenter.requestHotMovieById(movie);
          /*回退键触发事件*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    @Override
    public void initPresenter() {
        presenter = new MoviePresenterImpl(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_movie_detils;
    }

    @Override
    public void returnMusicInfo(List<Movie.SubjectsBean> movie) {

    }


    @Override
    public void OnItemClick(ImageView view, int position, String viewid) {
        Intent intent=new Intent(this,ActorActivity.class);
        intent.putExtra("viewid",viewid);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation((Activity) mContext, view, AppContent.TRANSITION_IMAGE_MOVIE);
            mContext.startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());
        }
    }



    @Override
    public void returnMusicInfoDetils(final MovieDetils movieDetils) {

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (AppContent.state != AppContent.CollapsingToolbarLayoutState.EXPANDED) {
                        AppContent.state = AppContent.CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        toolbarLayout.setTitle("");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (AppContent.state != AppContent.CollapsingToolbarLayoutState.COLLAPSED) {
                        toolbarLayout.setTitle(movieDetils.getTitle());
                        AppContent.state = AppContent.CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                }
            }
        });

        //电影名称
        name.setText(movieDetils.getTitle());
        //导演
        dierct.setText(movieDetils.getDirectors().get(0).getName());
        //演员
        List<MovieDetils.CastsBean> castbean = movieDetils.getCasts();
        String acts = "";
        for (MovieDetils.CastsBean bean : castbean) {
            acts = acts.concat(bean.getName()+" ");
        }
        act.setText(acts);
        //图片
        ImageLoaderUtils.display(this,ivPhoto,movieDetils.getImages().getLarge());
        //类型
        String type_movie="";
        List<String> genres=movieDetils.getGenres();
        for (String gen : genres) {
            type_movie = type_movie.concat(gen+" ");
        }
        type.setText(type_movie);
        //日期
        data.setText(movieDetils.getYear());
        //国家
        county.setText(movieDetils.getCountries().toString());

        //评分
        tvRating.setText(String.valueOf( movieDetils.getRating().getAverage()));
        //简介
        content.setText(movieDetils.getSummary());


        LinearLayoutManager manager=new LinearLayoutManager(MovieDetilsActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvCast.setLayoutManager(manager);
        xrvCast.setVisibility(View.VISIBLE);

        xrvCast.setPullRefreshEnabled(false);
        xrvCast.setItemAnimator(new DefaultItemAnimator());
        xrvCast.setLoadingMoreEnabled(false);
        xrvCast.setNestedScrollingEnabled(false);
        xrvCast.setHasFixedSize(false);
        MovieDetailAdapter adapter=new MovieDetailAdapter(movieDetils.getCasts(),MovieDetilsActivity.this);
        adapter.setOnItemDetilsClickListener(this);
        xrvCast.setAdapter(adapter);

        LoadingDialog.cancelDialogForLoading();

    }

    @Override
    public void returnNewMovie(List<NewMovie.SubjectsBean> newMovie) {

    }


}
