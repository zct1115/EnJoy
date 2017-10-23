package com.ckkj.enjoy.api;

import com.ckkj.enjoy.bean.Login;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/10/22.
 */

public interface UserApi {
    public static String USER_URL=" http://192.168.56.1:9010/";


    @GET("user/login")
    Observable<Login> loginchecked(@Query("name") String name,@Query("password") String password);

    @GET("user/register")
    Observable<Login> signup(@Query("name") String name,@Query("password") String password);
}
