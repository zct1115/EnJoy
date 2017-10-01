package com.ckkj.enjoy.ui.music.view;


import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.bean.SongListDetail;

import java.util.List;

/**
 * Created by lvr on 2017/4/27.
 */

public interface MusicSongListDetailView {
    void returnSongListDetailInfos(List<SongListDetail.SongDetail> songDetails);
    void returnSongDetail(SongDetailInfo info);
}
