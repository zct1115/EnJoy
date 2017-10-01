package com.ckkj.enjoy.ui.music.model;

import com.ckkj.enjoy.bean.RankingListDetail;
import com.ckkj.enjoy.bean.RankingListItem;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.bean.SongListDetail;
import com.ckkj.enjoy.bean.WrapperSongListInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by zct11 on 2017/9/25.
 */

public interface MusicModelImp {
    Observable<List<WrapperSongListInfo.SongListInfo>> loadSongListAll(String format, String from, String method, int page_size, int page_no);
    Observable<List<RankingListItem.RangkingDetail>> getRankinglist(String format,String from,String method,int kflag);
    Observable<RankingListDetail> loadRankListDetail(String format, String from, String method, int type, int offset, int size, String fields );
    Observable<SongDetailInfo> loadSongDetail(String from, String version, String format, String method, String songid);
    Observable<List<SongListDetail.SongDetail>> loadSongListDetail(String format, String from, String method, String listid);
}
