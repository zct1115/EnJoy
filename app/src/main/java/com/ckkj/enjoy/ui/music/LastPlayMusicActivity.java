package com.ckkj.enjoy.ui.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aspsine.irecyclerview.IRecyclerView;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.SongDetailInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LastPlayMusicActivity extends BaseActivity {


    SongDetailInfo songDetailInfo = new SongDetailInfo();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycle)
    IRecyclerView recycle;


    @Override
    public void initView() {
        Intent intent = getIntent();
        ArrayList<SongDetailInfo.SonginfoBean> data = (ArrayList<SongDetailInfo.SonginfoBean>) intent.getSerializableExtra("song");
        toolbar.setTitle("最近播放");
        //设置toolbar 回退点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setRecycle(data);
    }

    private void setRecycle(ArrayList<SongDetailInfo.SonginfoBean> data) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_last_play_music;
    }



}
