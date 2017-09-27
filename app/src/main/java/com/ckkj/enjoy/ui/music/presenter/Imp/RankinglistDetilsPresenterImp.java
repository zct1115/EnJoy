package com.ckkj.enjoy.ui.music.presenter.Imp;

/**
 * Created by zct11 on 2017/9/25.
 */

public interface RankinglistDetilsPresenterImp {
    void requestRankListDetail(String format, String from, String method, int type, int offset, int size, String fields);
    void requestSongDetail(String from,String version,String format, String method,String songid);
}
