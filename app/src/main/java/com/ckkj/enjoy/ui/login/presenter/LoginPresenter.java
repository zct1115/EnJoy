package com.ckkj.enjoy.ui.login.presenter;

import com.ckkj.enjoy.ui.login.model.LoginModel;
import com.ckkj.enjoy.ui.login.model.LoginModelImp;
import com.ckkj.enjoy.ui.login.presenter.Imp.LoginPresenterImp;
import com.ckkj.enjoy.ui.login.view.Login;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/10/22.
 */

public class LoginPresenter implements LoginPresenterImp {

    private LoginModel model;
    private Login view;

    public LoginPresenter(Login view) {
        this.view = view;
        model=new LoginModel();
    }

    @Override
    public void login(String name, String password) {
        model.login(name,password).subscribe(new Observer<com.ckkj.enjoy.bean.Login>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(com.ckkj.enjoy.bean.Login login) {
                view.getMessage(login.getDesc());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void signup(String name, String password) {
        model.Signup(name,password).subscribe(new Observer<com.ckkj.enjoy.bean.Login>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(com.ckkj.enjoy.bean.Login login) {
                view.getMessage(login.getDesc());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
