package com.ckkj.enjoy.ui.home.model.Imp;

import com.ckkj.enjoy.api.MovieApiService;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.bean.OtherMovie;
import com.ckkj.enjoy.client.RetrofitClient;
import com.ckkj.enjoy.ui.home.model.OtherMovieModel;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by zct11 on 2017/10/3.
 */

public class OtherMovieModelImp implements OtherMovieModel{

    /**
     * 获取北美票房
     * @return
     */
    @Override
    public Observable<OtherMovie> getOtherMovie() {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(AppApplication.getAppContext(), MovieApiService.MOVIE_URL);
        MovieApiService api=retrofitClient.create(MovieApiService.class);
        return api.getOtherMovie().map(new Function<OtherMovie, OtherMovie>() {
            @Override
            public OtherMovie apply(@NonNull OtherMovie otherMovie) throws Exception {
                return otherMovie;
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
