package com.ckkj.enjoy.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.base.BaseFragmentAdapter;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.ui.home.tab.EnjoyFragment;
import com.ckkj.enjoy.ui.home.tab.EveryDayFragment;
import com.ckkj.enjoy.ui.home.tab.ListenFragment;
import com.ckkj.enjoy.ui.home.tab.StudyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主页面的fragment
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.tab_name)
    TabLayout tabName;
    @BindView(R.id.main_vg)
    ViewPager mainVg;
    //存放标题
    private ArrayList<String> mTitleList = new ArrayList<>(4);
    //存放BaseFragment
    private ArrayList<BaseFragment> mFragments = new ArrayList<>(4);

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        //初始化mFragments数据
        initFragmentList();
        //设置mFragments 适配器
        BaseFragmentAdapter adapter=new BaseFragmentAdapter(getChildFragmentManager(),mFragments,mTitleList);
        mainVg.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //设置默认从第0个开始
        mainVg.setCurrentItem(0);
        //设置页面最大限制为3个
        mainVg.setOffscreenPageLimit(3);
        //设置tab
        tabName.setTabMode(TabLayout.MODE_FIXED);
        //tab设置页面联动
        tabName.setupWithViewPager(mainVg);



    }

    private void initFragmentList() {
        mTitleList.add("每日推荐");
        mTitleList.add("享悦");
        mTitleList.add("聆听");
        mTitleList.add("学习");
        mFragments.add(new EveryDayFragment());
        mFragments.add(new EnjoyFragment());
        mFragments.add(new ListenFragment());
        mFragments.add(new StudyFragment());
    }

}
