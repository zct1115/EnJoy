package com.ckkj.enjoy.ui.music.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 歌单列表
 * Created by Administrator on 2017/9/26.
 */

public class SongListFragment extends BaseFragment {
    @BindView(R.id.ll_selector)
    LinearLayout llSelector;
    @BindView(R.id.irv_song_list)
    IRecyclerView irvSongList;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_songlist;
    }

    @Override
    protected void initView() {

    }

}
