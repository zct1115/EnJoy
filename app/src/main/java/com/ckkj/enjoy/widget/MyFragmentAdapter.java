package com.ckkj.enjoy.widget;

import android.support.v4.app.FragmentManager;

import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.base.BaseFragmentAdapter;

import java.util.List;

/**
 * Created by HiWin10 on 2017/9/23 0023.
 */

public class MyFragmentAdapter extends BaseFragmentAdapter {

    private List<?> mFragment;
    private List<String> mTitleList;

    public MyFragmentAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm, fragmentList);

    }
}
