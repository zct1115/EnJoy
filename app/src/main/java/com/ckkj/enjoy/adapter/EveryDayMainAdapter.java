package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.bean.OtherMovie;
import com.ckkj.enjoy.ui.home.tab.EveryDayFragment;
import com.ckkj.enjoy.utils.GlideLoader;
import com.ckkj.enjoy.utils.GlideLoaderUrl;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HiWin10 on 2017/9/23 0023.
 */

public class EveryDayMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private onItemClickListenr mOnItemClickListenr;
    private final Context context;
    private final LayoutInflater inflater;

    private static final int TYPE_BANNER=0;
    private static final int TYPE_MENU=1;
    private static final int TYPE_MOVIE_MORE=2;
    private static final int MOVIE=3;
    private static final int TYPE_MUSIC_MORE=4;


    private List<String> mImages = new ArrayList<>();
    private List<OtherMovie.SubjectsBean> mnewMovie;


    public EveryDayMainAdapter(Context context, List<OtherMovie.SubjectsBean>  newMovie) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mnewMovie=newMovie;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_BANNER;
        }else if(position==1){
            return TYPE_MENU;
        }else if(position==2){
            return TYPE_MOVIE_MORE;
        }else if(position==3){
            return MOVIE;
        }else{
            return TYPE_MUSIC_MORE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       switch (viewType){
           case TYPE_BANNER:
               return new BannerViewHolder(inflater.inflate(R.layout.item_home_banner,parent,false));
           case TYPE_MENU:
               return new MenuViewHolder(inflater.inflate(R.layout.layout_main_menu,parent,false));
           case TYPE_MOVIE_MORE:
               return new MovieDesViewHolder(inflater.inflate(R.layout.layout_new_movie, parent, false));
           case MOVIE:
               return new MovieViewHolder(inflater.inflate(R.layout.layout_new_movie_item, parent, false));
           case TYPE_MUSIC_MORE:
               return new MusicDesViewHolder(inflater.inflate(R.layout.layout_new_music, parent, false));

       }
       return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BannerViewHolder){
            setBannerItemValues((BannerViewHolder)holder,position);
        }else if(holder instanceof MenuViewHolder){
            setMenuItemValues((MenuViewHolder)holder,position);
        }else if(holder instanceof MovieViewHolder){
            setMovieValues((MovieViewHolder)holder,position);
        }else if(holder instanceof MovieDesViewHolder){
            setMovieDesValues((MovieDesViewHolder)holder,position);
        }else if(holder instanceof MusicDesViewHolder){
            setMusicDesValues((MusicDesViewHolder)holder,position);
        }
    }


    private void setMusicDesValues(MusicDesViewHolder holder, int position) {

    }

    private void setMovieDesValues(final MovieDesViewHolder holder, int position) {
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onTextClick(holder.more);
            }
        });
    }

    private void setMovieValues(MovieViewHolder holder, int position) {
        RecyclerView.LayoutManager manager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        NewMovieAdapter adapter=new NewMovieAdapter(mnewMovie,context) ;
        adapter.setOnItemDetilsClickListener(new MovieDetailAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(ImageView view, int position, String viewid) {
                mOnItemClickListenr.onItemMovie(view,position,viewid);
            }
        });
        holder.rv.setLayoutManager(manager);
        holder.rv.setAdapter(adapter);
    }

    private void setMenuItemValues(final MenuViewHolder holder, final int position) {
        holder.music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position,holder.music);
            }
        });
        holder.movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position,holder.movie);
            }
        });
        holder.listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position,holder.listen);
            }
        });
    }

    private void setBannerItemValues(BannerViewHolder holder, int position) {
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506185916011&di=00ecd8fdae6f853a1a4430a6a62955a7&imgtype=0&src=http%3A%2F%2Fwww.lidetx.com%2Fuploads%2F56befb6f37f52.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506780158&di=4db144169bbbd97dcc4596adb6797008&imgtype=jpg&er=1&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F78%2F37%2F69b1OOOPIC3f.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506186032123&di=26821d0dddb5751b1f7653e26c900092&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F77%2F47%2F63bOOOPIC74_1024.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506186314000&di=3b148298ceafd9819effc108bcfc7a8b&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140324%2F0032018034989862_b.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506186353687&di=4d22c83e081bd8939a05ad4764f7cac8&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F32%2F69%2F25e58PICyAR_1024.jpg");
        holder.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        holder.mBanner.setImageLoader(new GlideLoaderUrl());
        holder.mBanner.setImages(mImages);
        holder.mBanner.setBannerAnimation(Transformer.DepthPage);
        holder.mBanner.isAutoPlay(true);
        holder.mBanner.setDelayTime(1500);
        holder.mBanner.setIndicatorGravity(BannerConfig.CENTER);
        holder.mBanner.start();
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {

        private Banner mBanner;

        public BannerViewHolder(View v) {
            super(v);
            mBanner = (Banner) itemView.findViewById(R.id.banner_home);
        }
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {
        private ImageButton listen;
        private ImageButton music;
        private ImageButton movie;

        public MenuViewHolder(View v) {
            super(v);
            listen= (ImageButton) v.findViewById(R.id.ib_xiandu);
            music= (ImageButton) v.findViewById(R.id.ib_music_hot);
            movie= (ImageButton) v.findViewById(R.id.ib_movie_hot);

        }
    }

    public interface onItemClickListenr{
        void onItemClick(int position,ImageButton imageButton);
        void onTextClick(View view);
        void onItemMovie(ImageView v,int position,String id);
    }
    public void setOnItemClickListenr(onItemClickListenr onItemClickListenr){
        this.mOnItemClickListenr = onItemClickListenr;
    }

    private class MovieDesViewHolder extends RecyclerView.ViewHolder {
        private TextView more;
        public MovieDesViewHolder(View v) {
            super(v);
            more= (TextView) v.findViewById(R.id.more_movie);
        }
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv;

        public MovieViewHolder(View v) {
            super(v);
            rv= (RecyclerView) v.findViewById(R.id.new_movie);
        }
    }

    private class MusicDesViewHolder extends RecyclerView.ViewHolder {
        private TextView more;
        
        public MusicDesViewHolder(View v) {
            super(v);
            more= (TextView) v.findViewById(R.id.more_music_transceiver);
        }
    }

}
