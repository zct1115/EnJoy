package com.ckkj.enjoy.ui.music.model;

import com.ckkj.enjoy.api.MusicApiService;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.bean.RankingListDetail;
import com.ckkj.enjoy.bean.RankingListItem;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.bean.SongListDetail;
import com.ckkj.enjoy.bean.WrapperSongListInfo;
import com.ckkj.enjoy.client.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by zct11 on 2017/9/25.
 */

public class MusicModel implements MusicModelImp{

    /**
     * 获取全部歌单信息
     * @param format
     * @param from
     * @param method
     * @param page_size
     * @param page_no
     * @return
     */
    @Override
    public Observable<List<WrapperSongListInfo.SongListInfo>> loadSongListAll(String format, String from, String method, int page_size, int page_no) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance(AppApplication.getAppContext(), MusicApiService.MUSIC_URL);
        MusicApiService api = retrofitClient.create(MusicApiService.class);
        return api.getSongListAll(format, from, method, page_size, page_no).map(new Function<WrapperSongListInfo, List<WrapperSongListInfo.SongListInfo>>() {
            @Override
            public List<WrapperSongListInfo.SongListInfo> apply(WrapperSongListInfo info) throws Exception {
                return info.getContent();
            }
        }).compose(RetrofitClient.schedulersTransformer);//线程调度
    }

    @Override
    public Observable<List<RankingListItem.RangkingDetail>> getRankinglist(String format, String from, String method, int kflag) {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(AppApplication.getAppContext(),MusicApiService.MUSIC_URL);
        MusicApiService apiService=retrofitClient.create(MusicApiService.class);
        return apiService.getRankingListAll(format,from,method,kflag).map(new Function<RankingListItem, List<RankingListItem.RangkingDetail>>() {
            @Override
            public List<RankingListItem.RangkingDetail> apply(RankingListItem rankingListItem) throws Exception {
                return rankingListItem.getContent();
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<RankingListDetail> loadRankListDetail(String format, String from, String method, int type, int offset, int size, String fields) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance(AppApplication.getAppContext(), MusicApiService.MUSIC_URL);
        MusicApiService api = retrofitClient.create(MusicApiService.class);
        return api.getRankingListDetail(format,from,method,type,offset,size,fields).compose(RetrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<SongDetailInfo> loadSongDetail(String from, String version, String format, String method, String songid) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance(AppApplication.getAppContext(), MusicApiService.MUSIC_URL);
        MusicApiService api = retrofitClient.create(MusicApiService.class);
        return api.getSongDetail(from,version,format,  method, songid).compose(RetrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<List<SongListDetail.SongDetail>> loadSongListDetail(String format, String from, String method, String listid) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance(AppApplication.getAppContext(), MusicApiService.MUSIC_URL);
        MusicApiService api = retrofitClient.create(MusicApiService.class);
        return api.getSongListDetail(format, from, method, listid).map(new Function<SongListDetail, List<SongListDetail.SongDetail>>() {
            @Override
            public List<SongListDetail.SongDetail> apply(SongListDetail detail) throws Exception {
                return detail.getContent();
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
