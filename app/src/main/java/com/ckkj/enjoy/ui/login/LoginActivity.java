package com.ckkj.enjoy.ui.login;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.widget.ScrollView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.LoginViewPageAdapter;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.base.BaseActivityWithoutStatus;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.ui.login.fragment.FaceFragment;
import com.ckkj.enjoy.ui.login.fragment.UserFragment;
import com.ckkj.enjoy.utils.MyUtils;
import com.ckkj.enjoy.utils.StatusBarSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zct11 on 2017/10/3.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_title)
    TabLayout loginTitle;
    @BindView(R.id.login)
    ViewPager login;
    @BindView(R.id.sv_root)
    ScrollView svRoot;

    private int radius = 25;

    private List<BaseFragment> mFragments = new ArrayList<>();
    private FaceFragment facefragment;
    private UserFragment userfragment;
    private LoginViewPageAdapter adapter;
    private String[] title = {"账号密码登录", "刷脸登录"};



    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        StatusBarSetting.setTranslucent(this);
        //高斯模糊背景
        applyBlur();
    }

    private void applyBlur() {
        Drawable db = getResources().getDrawable(R.drawable.login_bg);
        BitmapDrawable drawable = (BitmapDrawable) db;
        Bitmap bgBitmap = drawable.getBitmap();
        //处理得到模糊效果的图
        RenderScript renderScript = RenderScript.create(mContext);
        // Allocate memory for Renderscript to work with
        final Allocation input = Allocation.createFromBitmap(renderScript, bgBitmap);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());
        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);
        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);
        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);
        // Copy the output to the blurred bitmap
        output.copyTo(bgBitmap);
        renderScript.destroy();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bgBitmap);
        svRoot.setBackground(bitmapDrawable);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            List<Fragment> fragments=getSupportFragmentManager().getFragments();
             for(Fragment fragment:fragments){
                 if(userfragment instanceof UserFragment){
                     userfragment=(UserFragment) fragment;
                 }
                 if(facefragment instanceof FaceFragment){
                     facefragment=(FaceFragment) fragment;
                 }
             }
        }else {
            //添加到FragmentManger，异常时，自动保存Fragment状态
            userfragment=new UserFragment();
            facefragment=new FaceFragment();
        }
        setViewPager();
    }

    private void setViewPager() {
        mFragments.add(userfragment);
        mFragments.add(facefragment);
        adapter=new LoginViewPageAdapter(getSupportFragmentManager(),mFragments, Arrays.asList(title));
        login.setAdapter(adapter);
        login.setCurrentItem(0);
        loginTitle.setupWithViewPager(login);
        MyUtils.dynamicSetTabLayoutMode(loginTitle);
        login.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 login.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }




}
