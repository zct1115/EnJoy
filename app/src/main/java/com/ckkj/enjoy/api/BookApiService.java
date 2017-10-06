package com.ckkj.enjoy.api;

import com.ckkj.enjoy.bean.BookBean;
import com.ckkj.enjoy.bean.BookDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zct11 on 2017/10/5.
 */

public interface BookApiService {

    public static final String BOOK_URL="https://api.douban.com/";

    /**
     * 根据tag获取图书
     *
     * @param tag   搜索关键字
     * @param count 一次请求的数目 最多100
     */

    @GET("v2/book/search")
    Observable<BookBean> getBook(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("v2/book/{id}")
    Observable<BookDetailBean> getBookDetail(@Path("id") String id);
}
