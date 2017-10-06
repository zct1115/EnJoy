package com.ckkj.enjoy.ui.book.view;

import com.ckkj.enjoy.bean.BookBean;

import java.util.List;

/**
 * Created by zct11 on 2017/10/5.
 */

public interface BookListView {
    void getBooklist(List<BookBean.BooksBean> bookBean);
}
