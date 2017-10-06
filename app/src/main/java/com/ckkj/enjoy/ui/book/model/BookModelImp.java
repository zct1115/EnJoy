package com.ckkj.enjoy.ui.book.model;

import com.ckkj.enjoy.bean.BookBean;
import com.ckkj.enjoy.bean.BookDetailBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by zct11 on 2017/10/5.
 */

public interface BookModelImp
{
    Observable<List<BookBean.BooksBean>> getBooklist(String tag, int start, int count);
    Observable<BookDetailBean> getBookDetils(String id);
}
