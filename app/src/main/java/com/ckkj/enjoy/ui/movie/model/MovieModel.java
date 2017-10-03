package com.ckkj.enjoy.ui.movie.model;

import android.util.Log;

import com.ckkj.enjoy.api.MovieApiService;
import com.ckkj.enjoy.api.MusicApiService;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.client.RetrofitClient;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Ting on 2017/9/17.
 */

public class MovieModel implements MovieImp {
    /**
     * 获取电影列表
     * @return
     */
    @Override
    public Observable<List<Movie.SubjectsBean>> getHotMovie(int count,int start) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance(AppApplication.getAppContext(), MovieApiService.MOVIE_URL);
        MovieApiService api=retrofitClient.create(MovieApiService.class);
    return api.getHotMovie(count,start).map(new Function<Movie, List<Movie.SubjectsBean>>() {
        @Override
        public List<Movie.SubjectsBean> apply(Movie movie) throws Exception {
            List<Movie.SubjectsBean> data=movie.getSubjects();
            return data;
        }
    }).compose(retrofitClient.schedulersTransformer);
    }

    /**
     * 通过id获取电影的详细信息
     * @param id
     * @return
     */
    @Override
    public Observable<MovieDetils> getMovieById(String id) {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(AppApplication.getAppContext(),MovieApiService.MOVIE_URL);
        MovieApiService api=retrofitClient.create(MovieApiService.class);
        return api.getHotMovieById(id).map(new Function<MovieDetils, MovieDetils>() {
            @Override
            public MovieDetils apply(MovieDetils movieDetils) throws Exception {
                return movieDetils;
            }
        }).compose(retrofitClient.schedulersTransformer);
    }

    /**
     * 获取最新电影
     * @param count
     * @param start
     * @return
     */
    @Override
    public Observable<List<NewMovie.SubjectsBean>> getnewMovie(int count, int start) {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(AppApplication.getAppContext(),MovieApiService.MOVIE_URL);
        MovieApiService api=retrofitClient.create(MovieApiService.class);
        return api.getNewMovie(count,start).map(new Function<NewMovie, List<NewMovie.SubjectsBean>>() {
            @Override
            public List<NewMovie.SubjectsBean> apply(NewMovie newMovie) throws Exception {
                return newMovie.getSubjects();
            }
        }).compose(retrofitClient.schedulersTransformer);
    }

}
