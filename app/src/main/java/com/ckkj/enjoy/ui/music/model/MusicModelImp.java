package com.ckkj.enjoy.ui.music.model;

import com.ckkj.enjoy.bean.WrapperSongListInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by zct11 on 2017/9/25.
 */

public interface MusicModelImp {
    Observable<List<WrapperSongListInfo.SongListInfo>> getWrapperSongListInfo(String format, String from, String method, int page_size, int page_no);
}
