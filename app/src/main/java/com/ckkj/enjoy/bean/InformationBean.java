package com.ckkj.enjoy.bean;

/**
 * Created by Administrator on 2017/10/13.
 */

public class InformationBean {
    private String title;
    private String content;
    private boolean isSet;

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
