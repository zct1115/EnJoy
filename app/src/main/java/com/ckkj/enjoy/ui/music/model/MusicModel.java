package com.ckkj.enjoy.ui.music.model;

import com.ckkj.enjoy.api.MusicApiService;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.bean.WrapperSongListInfo;
import com.ckkj.enjoy.client.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by zct11 on 2017/9/25.
 */

public class MusicModel implements MusicModelImp{

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
}
