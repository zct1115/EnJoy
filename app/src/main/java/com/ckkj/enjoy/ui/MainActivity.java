package com.ckkj.enjoy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.base.BaseFragmentAdapter;
import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.ui.home.MainFragment;
import com.ckkj.enjoy.ui.home.tab.EveryDayFragment;
import com.ckkj.enjoy.ui.movie.MoiveActivity;
import com.ckkj.enjoy.ui.movie.presenter.MoviePresenterImpl;
import com.ckkj.enjoy.ui.movie.view.MovieView;
import com.ckkj.enjoy.ui.music.MusicActivity;
import com.ckkj.enjoy.utils.StatusBarSetting;
import com.taobao.sophix.SophixManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.draweer_layout)
    DrawerLayout draweerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    private ImageView mIv_photo;
    private TextView mMTv_login;
    private Intent mIntent;

    private NewMovie mnewMovie;


    @Override
    public void initView() {
        StatusBarSetting.setColorForDrawerLayout(this, draweerLayout, getResources().getColor(R.color.colorPrimary), StatusBarSetting.DEFAULT_STATUS_BAR_ALPHA);
        setToolBar();
        setNavigationView();
        setHomeItemState();
        fab.setVisibility(View.GONE);
        initfragment();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    private void initfragment() {
        List<BaseFragment> mFragmentlist = new ArrayList<BaseFragment>();
        MainFragment mainFragment=new MainFragment();
        mFragmentlist.add(mainFragment);
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragmentlist);
       vpContent.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        vpContent.setOffscreenPageLimit(2);
        vpContent.setCurrentItem(0);

    }

    @Override
    public void initPresenter() {

    }


    private void setNavigationView() {
        //NavigationView初始化
        navigation.setItemIconTintList(null);
        View headerView = navigation.getHeaderView(0);
        mIv_photo = (ImageView) headerView.findViewById(R.id.iv_user_photo);
        mMTv_login = (TextView) headerView.findViewById(R.id.tv_login);
        navigation.setNavigationItemSelectedListener(this);
    }


    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    private void setToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        //菜单按钮可用
        actionBar.setHomeButtonEnabled(true);
        //回退按钮可用
        actionBar.setDisplayHomeAsUpEnabled(true);
        //将drawlayout与toolbar绑定在一起
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, draweerLayout, mToolbar, R.string.app_name, R.string.app_name);
        abdt.syncState();//初始化状态
        //设置drawlayout的监听事件 打开/关闭
        draweerLayout.setDrawerListener(abdt);
        //actionbar中的内容进行初始化
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.mn_home: {
                //不需要做
                break;
            }
            case R.id.mn_movie: {
                MoiveActivity.startAction(MainActivity.this);
                break;
            }
            case R.id.mn_music: {
                startActivity(new Intent(this,MusicActivity.class));
                break;
            }
            case R.id.mn_information: {
                break;
            }
            case R.id.mn_about: {
                break;
            }
            case R.id.mn_out: {
                break;
            }
            case R.id.mn_guess: {
                Toast.makeText(MainActivity.this, "该功能还未实现，敬请期待", Toast.LENGTH_SHORT).show();
                break;
            }

        }
        item.setChecked(true);
        draweerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 设置首页默认被选中的状态
     */
    private void setHomeItemState() {
        Menu menu = navigation.getMenu();
        MenuItem item = menu.getItem(0);
        //更多中  特殊情况  取消选中状态
        menu.getItem(5).getSubMenu().getItem(0).setChecked(false);
        menu.getItem(5).getSubMenu().getItem(1).setChecked(false);
        item.setChecked(true);
    }

    @Override
    public void onBackPressed() {
        if (draweerLayout.isDrawerOpen(GravityCompat.START)) {
            draweerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (draweerLayout.isDrawerOpen(GravityCompat.START)) {
                draweerLayout.closeDrawer(GravityCompat.START);
            } else {
                // 不退出程序，进入后台
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
