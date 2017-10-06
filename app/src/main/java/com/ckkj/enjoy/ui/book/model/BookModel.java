package com.ckkj.enjoy.ui.book.model;

import android.support.v7.widget.RecyclerView;

import com.ckkj.enjoy.api.BookApiService;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.bean.BookBean;
import com.ckkj.enjoy.bean.BookDetailBean;
import com.ckkj.enjoy.client.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by zct11 on 2017/10/5.
 */

public class BookModel implements BookModelImp {
    @Override
    public Observable<List<BookBean.BooksBean>> getBooklist(String tag, int start, int count) {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(AppApplication.getAppContext(), BookApiService.BOOK_URL);
        BookApiService api=retrofitClient.create(BookApiService.class);
        return api.getBook(tag, start, count).map(new Function<BookBean, List<BookBean.BooksBean>>() {
            @Override
            public List<BookBean.BooksBean> apply(@NonNull BookBean bookBean) throws Exception {
                List<BookBean.BooksBean> data=bookBean.getBooks();
                return data;
            }
        }).compose(retrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<BookDetailBean> getBookDetils(String id) {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(AppApplication.getAppContext(),BookApiService.BOOK_URL);
        BookApiService api=retrofitClient.create(BookApiService.class);
        return api.getBookDetail(id).map(new Function<BookDetailBean, BookDetailBean>() {
            @Override
            public BookDetailBean apply(@NonNull BookDetailBean bookDetailBean) throws Exception {
                return bookDetailBean;
            }
        }).compose(retrofitClient.schedulersTransformer);
    }
}
