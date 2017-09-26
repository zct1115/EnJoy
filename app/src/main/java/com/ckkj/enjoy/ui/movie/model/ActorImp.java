package com.ckkj.enjoy.ui.movie.model;

import com.ckkj.enjoy.bean.ActorDetils;

import io.reactivex.Observable;

/**
 * Created by HiWin10 on 2017/9/21 0021.
 */

public interface ActorImp {
    Observable<ActorDetils> getActor(String id);
}
