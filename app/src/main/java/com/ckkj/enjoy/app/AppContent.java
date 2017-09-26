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


}
