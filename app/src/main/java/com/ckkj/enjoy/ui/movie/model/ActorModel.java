package com.ckkj.enjoy.ui.movie.model;

import com.ckkj.enjoy.api.MovieApiService;
import com.ckkj.enjoy.api.MusicApiService;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.bean.ActorDetils;
import com.ckkj.enjoy.client.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 * Created by HiWin10 on 2017/9/21 0021.
 */

public class ActorModel implements ActorImp {
    @Override
    public Observable<ActorDetils> getActor(String id) {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(AppApplication.getAppContext(), MovieApiService.MOVIE_URL);
        MovieApiService api=retrofitClient.create(MovieApiService.class);
        return api.getActorById(id).map(new Function<ActorDetils, ActorDetils>() {
            @Override
            public ActorDetils apply(ActorDetils actorDetils) throws Exception {
                return actorDetils;
            }
        }).compose(retrofitClient.schedulersTransformer);
    }
}
