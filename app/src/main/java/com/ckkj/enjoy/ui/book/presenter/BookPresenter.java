package com.ckkj.enjoy.ui.book.presenter;

import com.ckkj.enjoy.bean.BookBean;
import com.ckkj.enjoy.ui.book.model.BookModel;
import com.ckkj.enjoy.ui.book.presenter.Imp.BookPresenterImp;
import com.ckkj.enjoy.ui.book.view.BookListView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zct11 on 2017/10/5.
 */

public class BookPresenter implements BookPresenterImp {

    private BookListView view;
    private BookModel model;

    public BookPresenter(BookListView view) {
        this.view = view;
        model=new BookModel();
    }

    /**
     * 获取图书列表
     * @param tag 关键字
     * @param start 0
     * @param count 数量
     */
    @Override
    public void getBooklist(String tag, int start, int count) {
        model.getBooklist(tag, start, count).subscribe(new Observer<List<BookBean.BooksBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<BookBean.BooksBean> booksBeen) {
              view.getBooklist(booksBeen);
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
