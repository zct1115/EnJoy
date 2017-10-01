package com.ckkj.enjoy.ui.music;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.bean.SongDetailInfo;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;

public class LastPlayMusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_play_music);
        Bundle bundle=getIntent().getBundleExtra("bundle");
        int t=bundle.getInt("num");
        Log.d("LastPlayMusicActivity", "t:" + t);
    }

  /*  @Subscribe
    public void getLastMusic(SongDetailInfo songDetailInfo){
        List<SongDetailInfo> list=new ArrayList<>();
        list.add(songDetailInfo);
        Log.d("LastPlayMusicActivity", "list.size():" + list.size());
    }*/
}
