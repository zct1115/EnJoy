package com.ckkj.enjoy.ui.movie.view;

import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;

import java.util.List;

/**
 * Created by Ting on 2017/9/17.
 */

public interface MovieView {

    void returnMusicInfo(List<Movie.SubjectsBean> movie);

    void returnMusicInfoDetils(MovieDetils movieDetils);

    void returnNewMovie(List<NewMovie.SubjectsBean> newMovie);
}
