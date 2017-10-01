package com.ckkj.enjoy.ui.music.presenter;

import android.content.Context;

import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.bean.SongListDetail;
import com.ckkj.enjoy.ui.music.model.MusicModel;
import com.ckkj.enjoy.ui.music.presenter.Imp.MusicSongListDetailPresenter;
import com.ckkj.enjoy.ui.music.view.MusicSongListDetailView;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lvr on 2017/4/27.
 */

public class MusicSongListDetailPresenterImpl implements MusicSongListDetailPresenter {
    private MusicSongListDetailView mView;
    private MusicModel mMusicModel;
    private Context mContext;

    public MusicSongListDetailPresenterImpl(MusicSongListDetailView view, Context context) {
        this.mView = view;
        this.mMusicModel = new MusicModel();
        this.mContext = context;
    }

    @Override
    public void requestSongListDetail(String format, String from, String method, String listid) {
        Observable<List<SongListDetail.SongDetail>> observable = mMusicModel.loadSongListDetail(format, from, method, listid);
        observable.subscribe(new Observer<List<SongListDetail.SongDetail>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<SongListDetail.SongDetail> songDetails) {
                mView.returnSongListDetailInfos(songDetails);
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
        Observable<SongDetailInfo> observable = mMusicModel.loadSongDetail(from, version, format, method, songid);
        observable.subscribe(new Observer<SongDetailInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SongDetailInfo info) {
                mView.returnSongDetail(info);
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
