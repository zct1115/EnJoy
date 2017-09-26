package com.ckkj.enjoy.ui.movie.model;

import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ting on 2017/9/17.
 */

public interface MovieImp {
    Observable<List<Movie.SubjectsBean>> getHotMovie();
    Observable<MovieDetils> getMovieById(String id);
    Observable<NewMovie> getnewMovie(int count,int start);

}
