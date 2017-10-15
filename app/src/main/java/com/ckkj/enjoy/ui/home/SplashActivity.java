package com.ckkj.enjoy.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.base.BaseActivityWithoutStatus;
import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.ui.MainActivity;
import com.ckkj.enjoy.ui.home.tab.EveryDayFragment;
import com.ckkj.enjoy.ui.movie.presenter.MoviePresenterImpl;
import com.ckkj.enjoy.ui.movie.view.MovieView;
import com.ckkj.enjoy.utils.GlideLoader;
import com.ckkj.enjoy.utils.StatusBarSetting;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 启动页采用轮播图的形式
 */
public class SplashActivity extends BaseActivityWithoutStatus {

    @BindView(R.id.banner_splash)
    Banner bannerSplash;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.activity_splash)
    RelativeLayout activitySplash;


    @OnClick(R.id.text)
    public void onViewClicked() {
        startActivity(new Intent(this, MainActivity.class));
        //设置页面切换动画效果
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
    }

    @Override
    public void initView() {
        //设置状态栏为半透明
        StatusBarSetting.setTranslucent(this);
        //存放图片的id
        ArrayList<Integer> mDrawables = new ArrayList<>();
        mDrawables.add(R.mipmap.welcome);
        mDrawables.add(R.mipmap.welcome1);
        //banner设置图片加载
        bannerSplash.setImages(mDrawables).setImageLoader(new GlideLoader()).start();
        //banner设置自动轮播
        bannerSplash.isAutoPlay(true);
        //设置动画效果
        bannerSplash.setBannerAnimation(Transformer.DepthPage);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {

    }


}
