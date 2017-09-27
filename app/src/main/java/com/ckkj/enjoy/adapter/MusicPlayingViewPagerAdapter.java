package com.ckkj.enjoy.adapter;

import android.support.v4.app.FragmentManager;


import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.base.BaseFragmentAdapter;

import java.util.List;

/**
 * Created by lvr on 2017/5/2.
 */

public class MusicPlayingViewPagerAdapter extends BaseFragmentAdapter {
    public MusicPlayingViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm, fragmentList);
    }
}
