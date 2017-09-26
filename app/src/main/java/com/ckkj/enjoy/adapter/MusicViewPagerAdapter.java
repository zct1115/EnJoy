package com.ckkj.enjoy.adapter;

import android.support.v4.app.FragmentManager;

import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.base.BaseFragmentAdapter;

import java.util.List;

/**
 * Created by lvr on 2017/2/6.
 */

public class MusicViewPagerAdapter extends BaseFragmentAdapter {
    public MusicViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<String> mTitles) {
        super(fm, fragmentList, mTitles);
    }

    public MusicViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm, fragmentList);
    }
}
