package com.ckkj.enjoy.api;

import com.ckkj.enjoy.bean.RankingListDetail;
import com.ckkj.enjoy.bean.RankingListItem;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.bean.SongListDetail;
import com.ckkj.enjoy.bean.WrapperSongListInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Ting on 2017/9/17.
 */

public interface MusicApiService {

    public static String MUSIC_URL="http://tingapi.ting.baidu.com/v1/restserver/";


    /**
     *
     * 获取全部歌单
     * @param format  格式分为json或者xml
     * @param from   webapp_music
     * @param method  method=baidu.ting.billboard.billList&type=1&size=10&offset=0
     * @param page_size 返回条目数量
     * @param page_no 获取偏移
     * @return
     */
    /*	type = 1-新歌榜,2-热歌榜,11-摇滚榜,12-爵士,16-流行,21-欧美金曲榜,22-经典老歌榜,23-情歌对唱榜,24-影视金曲榜,25-网络歌曲榜*/
    /*http://tingapi.ting.baidu.com/v1/restserver/ting?format=json%E6%88%96xml&calback=&from=webapp_music&method=baidu.ting.billboard.billList&type=1&size=10&offset=0*/
    @GET("ting")
    @Headers("user-agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    Observable<WrapperSongListInfo> getSongListAll(@Query("format") String format,
                                                   @Query("from") String from,
                                                   @Query("method") String method,
                                                   @Query("page_size") int page_size,
                                                   @Query("page_no") int page_no);

    //获取全部榜单
    @GET("ting")
    @Headers("user-agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    Observable<RankingListItem> getRankingListAll(@Query("format") String format,
                                                  @Query("from") String from,
                                                  @Query("method") String method,
                                                  @Query("kflag") int kflag);

    //获取某个榜单中歌曲信息
    @GET("ting")
    @Headers("user-agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    Observable<RankingListDetail> getRankingListDetail(@Query("format") String format,
                                                       @Query("from") String from,
                                                       @Query("method") String method,
                                                       @Query("type") int type,
                                                       @Query("offset") int offset,
                                                       @Query("size") int size,
                                                       @Query("fields") String fields);

    //获取某个歌单中的信息
    @GET("ting")
    @Headers("user-agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    Observable<SongListDetail> getSongListDetail(@Query("format") String format,
                                                 @Query("from") String from,
                                                 @Query("method") String method,
                                                 @Query("listid") String listid);

    //获取某个歌曲的信息
    @GET("ting")
    @Headers("user-agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    Observable<SongDetailInfo> getSongDetail(@Query("from") String from,
                                             @Query("version") String version,
                                             @Query("format") String format,
                                             @Query("method") String method,
                                             @Query("songid") String songid);
}
