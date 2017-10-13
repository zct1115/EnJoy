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
import com.ckkj.enjoy.utils.ImageLoaderUtils;

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
        return new SongViewHolder(inflater.inflate(R.layout.item_music_last_play,parent,false));
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
      if(holder instanceof SongViewHolder){
          setLastSongValue(holder,position);
      }
    }

    private void setLastSongValue(SongViewHolder holder, int position) {
        ImageLoaderUtils.display(context,holder.img,data.get(position).getPic_big());
        holder.song.setText(data.get(position).getTitle());
        holder.songer.setText(data.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView songer;
        public TextView song;

        public SongViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.song_img);
            songer= (TextView) itemView.findViewById(R.id.singer);
            song= (TextView) itemView.findViewById(R.id.song_name);
        }
    }


}
