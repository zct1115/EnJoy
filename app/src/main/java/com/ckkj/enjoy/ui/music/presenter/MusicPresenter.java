package com.ckkj.enjoy.ui.music.presenter;

import com.ckkj.enjoy.bean.WrapperSongListInfo;
import com.ckkj.enjoy.ui.music.model.MusicModel;
import com.ckkj.enjoy.ui.music.view.MusicView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zct11 on 2017/9/25.
 */

public class MusicPresenter implements MusicPresenterImp {

    private MusicModel model;
    private MusicView musicView;

    public MusicPresenter(MusicView musicView) {
        this.musicView = musicView;
        this.model=new MusicModel();
    }

    @Override
    public void requestSongListAll(String format, String from, final String method, int page_size, int page_no) {
         model.loadSongListAll(format,from,method,page_size,page_no).subscribe(new Observer<List<WrapperSongListInfo.SongListInfo>>() {
             @Override
             public void onSubscribe(Disposable d) {

             }

             @Override
             public void onNext(List<WrapperSongListInfo.SongListInfo> songListInfos) {
                musicView.returnSongListInfos(songListInfos);
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
