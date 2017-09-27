package com.ckkj.enjoy.ui.music.view;

import com.ckkj.enjoy.bean.RankingListDetail;
import com.ckkj.enjoy.bean.SongDetailInfo;

/**
 * Created by zct11 on 2017/9/27.
 */

public interface MusicRankingListDetailView {
    void returnSongDetail(SongDetailInfo info);
    void returnRankingListDetail(RankingListDetail detail);
}
