package com.ckkj.enjoy.ui.home.presenter.Imp;

import com.ckkj.enjoy.bean.OtherMovie;
import com.ckkj.enjoy.ui.home.model.Imp.OtherMovieModelImp;
import com.ckkj.enjoy.ui.home.model.OtherMovieModel;
import com.ckkj.enjoy.ui.home.presenter.OtherMoviePresenter;
import com.ckkj.enjoy.ui.home.view.OtherMovieView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zct11 on 2017/10/3.
 */

public class OtherMoviePresenterImp implements OtherMoviePresenter {

    private OtherMovieView view;
    private OtherMovieModelImp model;

    public OtherMoviePresenterImp(OtherMovieView view) {
        this.view = view;
        model=new OtherMovieModelImp();
    }

    @Override
    public void getOtherMovie() {
         model.getOtherMovie().subscribe(new Observer<OtherMovie>() {
             @Override
             public void onSubscribe(Disposable d) {

             }

             @Override
             public void onNext(OtherMovie otherMovie) {
                     view.getOtherMovieView(otherMovie);
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
