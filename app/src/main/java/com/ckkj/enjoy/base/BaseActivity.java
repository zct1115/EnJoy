package com.ckkj.enjoy.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.app.AppManager;
import com.ckkj.enjoy.utils.StatusBarSetting;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类
 * Created by Ting on 2017/9/17.
 */

public abstract class BaseActivity extends AppCompatActivity{

    public Context mContext;
    private Unbinder unbinder;//绑定
    private int count;//记录开启进度条的情况，只能开一个

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContentView();
        setContentView(getLayoutID());
        SetStaTusBarColor();
        unbinder = ButterKnife.bind(this);
        mContext = this;
        this.initPresenter();
        this.initView();
    }

    /**
     * 初始化view
     */
    public abstract void initView() ;

    /**
     * 初始化presenter
     */
    public abstract void initPresenter();


    /**
     *  默认着色状况栏（4.4以上系统有效）
     */
    private void SetStaTusBarColor() {
    }

    /**
     * 获取布局文件的ID
     * @return ID
     */
    public abstract int getLayoutID();

    /**
     * 设置layout前配置
     */
    private void doBeforeSetContentView() {

        AppManager.getInstance().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarSetting.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     * @param color  颜色
     */
    protected void SetStatusBarColor(int color) {
        StatusBarSetting.setColor(this, color);
    }


    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarSetting.setTranslucent(this);
    }


    /**
     * 通过Class跳转界面
     * @param cls
     */
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     * @param cls
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }
    /**
     * 含有Bundle通过Class跳转界面
     * @param cls
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     * @param cls
     * @param bundle
     */
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        AppManager.getInstance().finishActivity(this);
    }



}
