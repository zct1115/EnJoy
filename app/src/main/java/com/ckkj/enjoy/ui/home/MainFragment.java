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
 * Created by HiWin10 on 2017/9/23 0023.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.tab_name)
    TabLayout tabName;
    @BindView(R.id.main_vg)
    ViewPager mainVg;

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<BaseFragment> mFragments = new ArrayList<>(4);

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        initFragmentList();

        BaseFragmentAdapter adapter=new BaseFragmentAdapter(getChildFragmentManager(),mFragments,mTitleList);
        mainVg.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mainVg.setCurrentItem(0);
        mainVg.setOffscreenPageLimit(3);
        tabName.setTabMode(TabLayout.MODE_FIXED);
        tabName.setupWithViewPager(mainVg);



    }

    private void initFragmentList() {
        mTitleList.add("每日推荐");
        mTitleList.add("享悦");
        mTitleList.add("聆听");
        mTitleList.add("学习");
        /*NewMovie newMovie=(NewMovie) getArguments().getSerializable("NewMovie");*/
        EveryDayFragment everyDayFragment=new EveryDayFragment();
        /*Bundle bundle=new Bundle();
        bundle.putSerializable("NewMovie",newMovie);
        everyDayFragment.setArguments(bundle);*/
        mFragments.add(everyDayFragment);
        mFragments.add(new EnjoyFragment());
        mFragments.add(new ListenFragment());
        mFragments.add(new StudyFragment());
    }

}
