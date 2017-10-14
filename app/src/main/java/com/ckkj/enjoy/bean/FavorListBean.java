package com.ckkj.enjoy.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class FavorListBean {
    private boolean isMovie;
    private List<String> mList;

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }

    public boolean isMovie() {
        return isMovie;
    }

    public void setMovie(boolean movie) {
        isMovie = movie;
    }
}
