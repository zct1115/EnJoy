package com.ckkj.enjoy.ui.home.tab.banner.model;

import com.ckkj.enjoy.bean.FrontpageBean;

import io.reactivex.Observable;

/**
 * Created by HiWin10 on 2017/9/23 0023.
 */

public interface BannerImp {
    Observable<FrontpageBean> getBanner();
}
