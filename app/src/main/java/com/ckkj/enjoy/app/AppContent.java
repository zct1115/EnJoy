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
    public static final String MUSIC_URL_VERSION = "5.6.5.6";
    public static final String MUSIC_URL_FROM_2 = "android";
    public static final  String MUSIC_URL_FROM = "webapp_music";
    public static final String MUSIC_URL_FORMAT = "json";
    public static final String MUSIC_URL_METHOD_GEDAN ="baidu.ting.diy.gedan";
    public static final String MUSIC_URL_METHOD_RANKINGLIST ="baidu.ting.billboard.billCategory";
    public static final String MUSIC_URL_METHOD_SONGLIST_DETAIL ="baidu.ting.diy.gedanInfo";
    public static final String MUSIC_URL_METHOD_SONG_DETAIL ="baidu.ting.song.play";
    public static final String MUSIC_URL_METHOD_RANKING_DETAIL ="baidu.ting.billboard.billList";
    public static final String MUSIC_URL_METHOD_RECOM ="baidu.ting.song.getEditorRecommend";
    public static final  int MUSIC_URL_RANKINGLIST_FLAG = 1;
    public static final int SENSOR_STATE_ERROR =-1;
    //列表循环
    public static final int PLAYING_MODE_REPEAT_ALL = 0;
    //随机播放
    public static final int PLAYING_MODE_SHUFFLE_NORMAL = 1;
    //单曲循环
    public static final int PLAYING_MODE_REPEAT_CURRENT = 2;

}
