package com.ckkj.enjoy.ui.movie.presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.client.RxDisposeManager;
import com.ckkj.enjoy.ui.movie.model.MovieModel;
import com.ckkj.enjoy.ui.movie.view.MovieView;
import com.ckkj.enjoy.utils.ACache;
import com.ckkj.enjoy.utils.CheckNetwork;
import com.ckkj.enjoy.widget.LoadingDialog;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ting on 2017/9/17.
 */

public class MoviePresenterImpl implements MoviePresenter {

    private MovieView mMovieView;
    private MovieModel model;
    private ACache aCache;

    public MoviePresenterImpl(MovieView movieView){
      this.mMovieView=movieView;
      this.model=new MovieModel();
    }

    @Override
    public void requestHotMovie() {
        model.getHotMovie().subscribe(new Observer<List<Movie.SubjectsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxDisposeManager.get().add("rating",d);
            }

            @Override
            public void onNext(List<Movie.SubjectsBean> movie) {
                mMovieView.returnMusicInfo(movie);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MoviePresenterImpl", "接收到错误信号");
                if(!CheckNetwork.isNetworkConnected((Activity)mMovieView)){
                    LoadingDialog.cancelDialogForLoading();
                    Toast.makeText((Activity)mMovieView, "当前无网络，请检查网络", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                Log.d("MoviePresenterImpl", "接收数据完成");
            }
        });
    }

    @Override
    public void requestHotMovieById(String id) {
        model.getMovieById(id).subscribe(new Observer<MovieDetils>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieDetils movieDetils) {
               mMovieView.returnMusicInfoDetils(movieDetils);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d("MoviePresenterImpl", "获取详细信息完毕");
            }
        });
    }

    /**
     * 获取最新电影
     * @param count
     * @param start
     */
    @Override
    public void getNewMovie(int count, int start) {
        model.getnewMovie(count,start).subscribe(new Observer<NewMovie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewMovie newMovie) {
                mMovieView.returnNewMovie(newMovie);
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
