package com.ckkj.enjoy.ui.music;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.Song;
import com.ckkj.enjoy.bean.SongDetailInfo;

import org.apache.log4j.chainsaw.Main;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class LastPlayMusicActivity extends BaseActivity {


    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_last_play_music;
    }


}
