package com.ckkj.enjoy.ui.login.fragment;


import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.widget.MySurfaceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/13.
 */

public class FaceFragment extends BaseFragment {

    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.faceview)
    SurfaceView faceview;



    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_login_face;
    }

    @Override
    protected void initView() {

    }



}
