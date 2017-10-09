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
    /**
     * 获取图书信息
     * @param tag  关键字
     * @param start  0
     * @param count  数量
     * @return
     */
    Observable<List<BookBean.BooksBean>> getBooklist(String tag, int start, int count);
    /**
     * 获取图书信息详情
     * @param id 图书id
     * @return 图书信息
     */
    Observable<BookDetailBean> getBookDetils(String id);
}
