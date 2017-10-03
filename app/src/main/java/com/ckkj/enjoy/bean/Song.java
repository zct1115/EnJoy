package com.ckkj.enjoy.bean;

/**
 * Created by zct11 on 2017/10/3.
 */

public class Song {
    private SongDetailInfo songDetailInfo;

    public Song(SongDetailInfo songDetailInfo) {
        this.songDetailInfo = songDetailInfo;
    }

    public SongDetailInfo getSongDetailInfo() {
        return songDetailInfo;
    }

    public void setSongDetailInfo(SongDetailInfo songDetailInfo) {
        this.songDetailInfo = songDetailInfo;
    }
}
