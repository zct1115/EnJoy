package com.ckkj.enjoy.ui.movie.presenter;

/**
 * Created by Ting on 2017/9/17.
 */

public interface MoviePresenter {
    void requestHotMovie(int count,int start);

    void requestHotMovieById(String id);

    void getNewMovie(int count,int start);
}
