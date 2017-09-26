package com.ckkj.enjoy.ui.music.view;

import com.ckkj.enjoy.bean.WrapperSongListInfo;

import java.util.List;

/**
 * Created by zct11 on 2017/9/25.
 */

public interface MusicView {

    void returnSongListInfos(List<WrapperSongListInfo.SongListInfo> songListInfos);
}
