package com.ckkj.enjoy.ui.music.view;

import com.ckkj.enjoy.bean.RankingListItem;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.bean.WrapperSongListInfo;

import java.util.List;

/**
 * Created by zct11 on 2017/9/25.
 */

public interface RankinglistView {

    void returnRankingListInfos(List<RankingListItem.RangkingDetail> rangkingListInfos);
}
