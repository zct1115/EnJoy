package com.ckkj.enjoy.ui.login.model;

import com.ckkj.enjoy.api.BookApiService;
import com.ckkj.enjoy.api.UserApi;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.bean.Login;
import com.ckkj.enjoy.client.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/10/22.
 */

public class LoginModel implements LoginModelImp {
    @Override
    public Observable<Login> login(String name, String password) {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(AppApplication.getAppContext(), UserApi.USER_URL);
        UserApi api=retrofitClient.create(UserApi.class);
        return api.loginchecked(name,password).map(new Function<Login, Login>() {
            @Override
            public Login apply(@NonNull Login login) throws Exception {
                return login;
            }
        }).compose(retrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<Login> Signup(String name, String password) {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(AppApplication.getAppContext(), UserApi.USER_URL);
        UserApi api=retrofitClient.create(UserApi.class);
        return api.signup(name,password).map(new Function<Login, Login>() {
            @Override
            public Login apply(@NonNull Login login) throws Exception {
                return login;
            }
        }).compose(retrofitClient.schedulersTransformer);
    }
}
