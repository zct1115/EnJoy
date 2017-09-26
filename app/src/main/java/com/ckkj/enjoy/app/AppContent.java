package com.ckkj.enjoy.app;

/**
 * Created by Ting on 2017/9/18.
 */

public class AppContent {
    public static final  String TRANSITION_IMAGE_MOVIE = "电影图片";

    /**
     * EXPANDED 展开
     * COLLAPSED 折叠
     *INTERNEDIATE 中间
     */
    public enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
    //下滑状态
    public static CollapsingToolbarLayoutState state;

    // 热映缓存
    public static String ONE_HOT_MOVIE = "one_hot_movie";


    public static final  String MUSIC_URL_FROM = "webapp_music";
    public static final String MUSIC_URL_FORMAT = "json";
    public static final String MUSIC_URL_METHOD_GEDAN ="baidu.ting.diy.gedan";
    public static final String MUSIC_URL_METHOD_RANKINGLIST ="baidu.ting.billboard.billCategory";
    public static final String MUSIC_URL_METHOD_SONGLIST_DETAIL ="baidu.ting.diy.gedanInfo";
    public static final String MUSIC_URL_METHOD_SONG_DETAIL ="baidu.ting.song.play";
    public static final String MUSIC_URL_METHOD_RANKING_DETAIL ="baidu.ting.billboard.billList";
    public static final String MUSIC_URL_METHOD_RECOM ="baidu.ting.song.getEditorRecommend";


}
