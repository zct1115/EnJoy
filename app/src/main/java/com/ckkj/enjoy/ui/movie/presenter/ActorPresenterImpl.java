package com.ckkj.enjoy.ui.movie.presenter;

import android.util.Log;

import com.ckkj.enjoy.bean.ActorDetils;
import com.ckkj.enjoy.ui.movie.model.ActorModel;
import com.ckkj.enjoy.ui.movie.view.ActorView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by HiWin10 on 2017/9/21 0021.
 */

public class ActorPresenterImpl implements ActorPresenter {

    private ActorModel actorModel;
    private ActorView actorView;

    public ActorPresenterImpl(ActorView actorView) {
        this.actorView = actorView;
        actorModel=new ActorModel();
    }

    @Override
    public void getActor(String id) {
        actorModel.getActor(id).subscribe(new Observer<ActorDetils>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ActorDetils actorDetils) {
               actorView.getActor(actorDetils);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
