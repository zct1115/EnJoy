package com.ckkj.enjoy.ui.book.presenter;

import com.ckkj.enjoy.bean.BookDetailBean;
import com.ckkj.enjoy.ui.book.model.BookModel;
import com.ckkj.enjoy.ui.book.presenter.Imp.BookDetilsPresenterImp;
import com.ckkj.enjoy.ui.book.view.BookDetilsView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zct11 on 2017/10/6.
 */

public class BookDetilsPresenter implements BookDetilsPresenterImp {

    private BookModel model;
    private BookDetilsView view;

    public BookDetilsPresenter(BookDetilsView view) {
        this.view = view;
        model=new BookModel();
    }

    @Override
    public void getBookdetils(String id) {
        model.getBookDetils(id).subscribe(new Observer<BookDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BookDetailBean bookDetailBean) {
               view.getBookdetils(bookDetailBean);
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
