package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.bean.SongDetailInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/9.
 */

public class LastSongPlayAdapter extends RecyclerView.Adapter<LastSongPlayAdapter.SongViewHolder> {


    private Context context;
    private LayoutInflater inflater;
    private ArrayList<SongDetailInfo.SonginfoBean> data;


    public LastSongPlayAdapter(Context context, ArrayList<SongDetailInfo.SonginfoBean> data) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LastSongViewHolder(inflater.inflate(R.layout.activity_last_play_music,parent,false));
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView songer;
        private TextView song;

        public SongViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class LastSongViewHolder extends SongViewHolder {

        public LastSongViewHolder(View v) {
            super(v);
        }
    }
}
