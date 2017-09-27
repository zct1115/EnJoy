package com.ckkj.enjoy.ui.music.presenter;

import com.ckkj.enjoy.bean.RankingListItem;

import com.ckkj.enjoy.ui.music.model.MusicModel;
import com.ckkj.enjoy.ui.music.presenter.Imp.RankinglistPresenterImp;
import com.ckkj.enjoy.ui.music.view.RankinglistView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zct11 on 2017/9/25.
 */

public class RankinglistPresenter implements RankinglistPresenterImp {

    private MusicModel model;
    private RankinglistView musicView;

    public RankinglistPresenter(RankinglistView musicView) {
        this.musicView = musicView;
        this.model=new MusicModel();
    }

    @Override
    public void requestRankinglist(String format, String from, String method, int kflag) {
        model.getRankinglist(format,from,method,kflag).subscribe(new Observer<List<RankingListItem.RangkingDetail>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<RankingListItem.RangkingDetail> rangkingDetails) {
                musicView.returnRankingListInfos(rangkingDetails);
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
