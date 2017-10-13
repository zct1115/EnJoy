package com.ckkj.enjoy.ui.login;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ScrollView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseActivityWithoutStatus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zct11 on 2017/10/3.
 */

public class LoginActivity extends BaseActivityWithoutStatus {
    @BindView(R.id.login_title)
    TabLayout loginTitle;
    @BindView(R.id.login)
    ViewPager login;
    @BindView(R.id.sv_root)
    ScrollView svRoot;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }


}
