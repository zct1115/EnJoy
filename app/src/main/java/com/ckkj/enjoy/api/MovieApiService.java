package com.ckkj.enjoy.api;

import com.ckkj.enjoy.bean.ActorDetils;
import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.bean.OtherMovie;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ting on 2017/9/17.
 */

public interface MovieApiService {
    public static final String MOVIE_URL="http://api.douban.com/";

    /**
     * 豆瓣热映电影，每日更新
     */
    @GET("v2/movie/in_theaters")
    Observable<Movie> getHotMovie(@Query("count") int count,@Query("start") int start);

    /**
     * 获取电影详情
     *
     * @param id 电影bean里的id
     */
    @GET("v2/movie/subject/{id}")
    Observable<MovieDetils> getHotMovieById(@Path("id") String id);

    /**
     * 获取演员信息
     */
    @GET("v2/movie/celebrity/{id}")
    Observable<ActorDetils> getActorById(@Path("id") String id);

    /**
     * 获取最新电影
     * @param count
     * @param start
     * @return
     */
   /*http://api.douban.com/v2/movie/coming_soon 没有权限*/
   @GET("v2/movie/coming_soon")
    Observable<NewMovie> getNewMovie(@Query("count") int count, @Query("start") int start);

    /*v2/movie/us_box */
   @GET("v2/movie/us_box")
    Observable<OtherMovie>getOtherMovie();

}
