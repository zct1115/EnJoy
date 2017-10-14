package com.ckkj.enjoy.adapter;

import android.support.v4.app.FragmentManager;

import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.base.BaseFragmentAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class LoginViewPageAdapter extends BaseFragmentAdapter{
    public LoginViewPageAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm, fragmentList);
    }

    public LoginViewPageAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<String> mTitles) {
        super(fm, fragmentList, mTitles);
    }
}
