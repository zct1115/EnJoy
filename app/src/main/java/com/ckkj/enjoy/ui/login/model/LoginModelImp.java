package com.ckkj.enjoy.ui.login.model;

import com.ckkj.enjoy.bean.Login;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/10/22.
 */

public interface LoginModelImp {

    Observable<Login> login(String name,String password);

    Observable<Login> Signup(String name,String password);
}
