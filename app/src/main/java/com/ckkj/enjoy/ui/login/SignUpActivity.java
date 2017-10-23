package com.ckkj.enjoy.ui.login;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.ui.login.presenter.LoginPresenter;
import com.ckkj.enjoy.ui.login.view.Login;
import com.ckkj.enjoy.utils.StatusBarSetting;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23.
 */

public class SignUpActivity extends BaseActivity implements Login {


    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_sign)
    AppCompatButton btnSign;
    @BindView(R.id.sv_root)
    ScrollView svRoot;
    @BindView(R.id.tv_turnlogin)
    TextView tvTurnlogin;
    private ProgressDialog mProgressDialog;
    private LoginPresenter presenter;

    private int radius = 25;

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
    public void initPresenter() {
       presenter=new LoginPresenter(this);
    }

    /**
     * 邮箱，密码是否格式正确
     *
     * @return
     */
    public boolean validate() {
        boolean valid = true;

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty() || !Patterns.PHONE.matcher(email).matches()) {
            inputEmail.setError("请输入有效的手机号码");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            inputPassword.setError("密码长度在4-10位之间");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }


    @Override
    public int getLayoutID() {
        return R.layout.activity_signup;
    }

    @OnClick({R.id.btn_sign, R.id.tv_turnlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign:
                if (!validate()) {
                    return;
                }

                btnSign.setEnabled(false);

                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("正在验证...");
                mProgressDialog.show();

                String userword = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                presenter.signup(userword,password);

                break;
            case R.id.tv_turnlogin:
                finish();
                break;
        }
    }

    @Override
    public void getMessage(String msg) {
        mProgressDialog.dismiss();
         if(msg.equals("OK")){
             Toast.makeText(this, "注册成功，请返回登录", Toast.LENGTH_SHORT).show();
             btnSign.setEnabled(true);
         }else {
             Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
             btnSign.setEnabled(true);
         }
    }
}
