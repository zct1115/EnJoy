package com.ckkj.enjoy.ui.home.model;

import com.ckkj.enjoy.bean.OtherMovie;

import io.reactivex.Observable;

/**
 * Created by zct11 on 2017/10/3.
 */

public interface OtherMovieModel {
    Observable<OtherMovie> getOtherMovie();
}
