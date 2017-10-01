package com.ckkj.enjoy.ui.music.presenter.Imp;

/**
 * Created by lvr on 2017/4/27.
 */

public interface MusicSongListDetailPresenter {
    void requestSongListDetail(String format, String from, String method, String listid);
    void requestSongDetail(String from, String version, String format, String method, String songid);
}
