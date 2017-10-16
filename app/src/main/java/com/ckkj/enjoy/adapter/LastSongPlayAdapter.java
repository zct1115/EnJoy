package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.database.music.MusicUtils;
import com.ckkj.enjoy.utils.ImageLoaderUtils;
import com.enjoy.entity.LastMusic;

import java.util.ArrayList;
import java.util.List;

/**
 * 最近音乐播放适配器
 * Created by Administrator on 2017/10/9.
 */

public class LastSongPlayAdapter extends RecyclerView.Adapter<LastSongPlayAdapter.SongViewHolder> {

    //上下文
    private Context context;
    //布局反射器
    private LayoutInflater inflater;

    private List<LastMusic> data;

    private onItemClickListenr mOnItemClickListenr;


    /**
     *
     * 构造器
     * @param context
     * @param data
     */
    public LastSongPlayAdapter(Context context,List<LastMusic>data) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.data = data;
    }

    /**
     * 创造viewholder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongViewHolder(inflater.inflate(R.layout.item_music_last_play,parent,false));
    }

    /**
     *
     * 绑定viewholder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
      if(holder instanceof SongViewHolder){
          setLastSongValue(holder,position);
      }
    }

    private void setLastSongValue(final SongViewHolder holder, final int position) {
        //加载图片
        ImageLoaderUtils.display(context,holder.img,data.get(position).getPic_big());

        holder.song.setText(data.get(position).getTitle());
        holder.songer.setText(data.get(position).getAuthor());
        holder.deleteimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicUtils utils=new MusicUtils(AppApplication.getAppContext());
                LastMusic lastMusic=new LastMusic();
                lastMusic.setId(data.get(position).getId());
                utils.deleteitemMusic(lastMusic);
            }
        });

        holder.addplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position,holder.addplay);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView songer;
        public TextView song;
        public ImageView deleteimg;
        public ImageView addplay;

        public SongViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.song_img);
            songer= (TextView) itemView.findViewById(R.id.singer);
            song= (TextView) itemView.findViewById(R.id.song_name);
            deleteimg= (ImageView) itemView.findViewById(R.id.delete_lastmusic);
            addplay= (ImageView) itemView.findViewById(R.id.addplay);
        }
    }

    public interface onItemClickListenr{
        void onItemClick(int position, ImageView imageView);
    }
    public void setOnItemClickListenr(onItemClickListenr onItemClickListenr){
        this.mOnItemClickListenr = onItemClickListenr;
    }





}
