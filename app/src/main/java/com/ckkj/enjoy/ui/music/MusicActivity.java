package com.ckkj.enjoy.ui.music;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.MusicMainViewPagerAdapter;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.ui.music.fragment.MusicDisplayFragment;
import com.ckkj.enjoy.ui.music.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.iv_bar_back)
    ImageView ivBarBack;
    @BindView(R.id.iv_bar_my)
    ImageView ivBarMy;
    @BindView(R.id.iv_bar_music)
    ImageView ivBarMusic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_music_main)
    ViewPager vpMusicMain;


    private List<ImageView> tabs;
    private MusicMainViewPagerAdapter mPagerAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private MyFragment mMyFragment;
    private MusicDisplayFragment mMusicDisplayFragment;

    @Override
    public void initView() {

        ivBarBack.setOnClickListener(this);
        ivBarMusic.setOnClickListener(this);
        ivBarMy.setOnClickListener(this);
        tabs = new ArrayList<>();
        tabs.add(ivBarMy);
        tabs.add(ivBarMusic);


}


    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_music;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_bar_back: {
                finish();
                break;
            }
            case R.id.iv_bar_music: {
                toolbar.setSelected(true);
                vpMusicMain.setCurrentItem(1);
                break;
            }
            case R.id.iv_bar_my: {
                ivBarMy.setSelected(true);
                vpMusicMain.setCurrentItem(0);
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            List<Fragment> fragments=getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragments) {
                if (fragment instanceof MyFragment) {
                    mMyFragment = (MyFragment) fragment;
                }
                if (fragment instanceof MusicDisplayFragment) {
                    mMusicDisplayFragment = (MusicDisplayFragment) fragment;
                }
            }
        }else {
            //添加到FragmentManger，异常时，自动保存Fragment状态
            mMyFragment = new MyFragment();
            mMusicDisplayFragment = new MusicDisplayFragment();
        }
        setViewPager();
    }

    /**
     * 设置ViewPager
     */
    private void setViewPager() {
        mFragments.add(mMyFragment);
        mFragments.add(mMusicDisplayFragment);
        mPagerAdapter = new MusicMainViewPagerAdapter(getSupportFragmentManager(), mFragments);
        vpMusicMain.setAdapter(mPagerAdapter);
        vpMusicMain.setCurrentItem(0);
        ivBarMy.setSelected(true);
        vpMusicMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * viewPager与Toolbar中的标签联动
     * @param position
     */
    private void switchTabs(int position) {
        for (int i = 0; i < tabs.size(); i++) {
            if (position == i) {
                tabs.get(i).setSelected(true);
            } else {
                tabs.get(i).setSelected(false);
            }
        }
    }
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MusicActivity.class);
        activity.startActivity(intent);
    }

}
