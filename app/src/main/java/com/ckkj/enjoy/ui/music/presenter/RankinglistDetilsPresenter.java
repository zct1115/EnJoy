package com.ckkj.enjoy.ui.music.presenter;

import com.ckkj.enjoy.bean.RankingListDetail;
import com.ckkj.enjoy.bean.RankingListItem;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.ui.music.model.MusicModel;
import com.ckkj.enjoy.ui.music.presenter.Imp.RankinglistDetilsPresenterImp;
import com.ckkj.enjoy.ui.music.presenter.Imp.RankinglistPresenterImp;
import com.ckkj.enjoy.ui.music.view.MusicRankingListDetailView;
import com.ckkj.enjoy.ui.music.view.RankinglistView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zct11 on 2017/9/25.
 */

public class RankinglistDetilsPresenter implements RankinglistDetilsPresenterImp {

    private MusicModel model;
    private MusicRankingListDetailView musicView;

    public RankinglistDetilsPresenter(MusicRankingListDetailView musicView) {
        this.musicView = musicView;
        this.model=new MusicModel();
    }


    @Override
    public void requestRankListDetail(String format, String from, String method, int type, int offset, int size, String fields) {
        model.loadRankListDetail(format,from,method,type,offset,size,fields).subscribe(new Observer<RankingListDetail>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RankingListDetail rankingListDetail) {
            musicView.returnRankingListDetail(rankingListDetail);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void requestSongDetail(String from, String version, String format, String method, String songid) {
        Observable<SongDetailInfo> observable = model.loadSongDetail(from, version, format, method, songid);
        observable.subscribe(new Observer<SongDetailInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SongDetailInfo info) {
                musicView.returnSongDetail(info);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
