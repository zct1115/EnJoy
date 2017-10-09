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

    /**
     * 获取图书详细信息
     * @param id 图书id
     */
    @Override
    public void getBookdetils(String id) {
        model.getBookDetils(id).subscribe(new Observer<BookDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            /**
             * 获取图书详细信息成功返回图书信息调用的方法
             * @param bookDetailBean
             */
            @Override
            public void onNext(BookDetailBean bookDetailBean) {
               view.getBookdetils(bookDetailBean);
            }

            /**
             * 获取图书信息出错调用的方法
             * @param e
             */
            @Override
            public void onError(Throwable e) {

            }

            /**
             * 获取图书详细信息完成时调用
             */
            @Override
            public void onComplete() {

            }
        });
    }
}
