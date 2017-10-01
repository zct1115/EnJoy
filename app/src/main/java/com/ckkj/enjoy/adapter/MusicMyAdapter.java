package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.bean.MusicMyItem;

import java.util.List;

/**
 * Created by lvr on 2017/4/25.
 */

public class MusicMyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_TOP = 0;
    private static final int TYPE_MIDDLE = 1;
    private static final int TYPE_BOTTOM = 2;
    private Context mContext;
    private List<MusicMyItem> mList;
    private final LayoutInflater inflater;
    private onItemClickListenr mOnItemClickListenr;//RecycleView监听事件


    public MusicMyAdapter(Context context, List<MusicMyItem> myItemList) {
        this.mContext = context;
        this.mList = myItemList;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if(position<=3){
            return TYPE_TOP;
        }else if(position==4){
            return TYPE_MIDDLE;
        }else{
            return TYPE_BOTTOM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TOP:
                return new TopViewHolder(inflater.inflate(R.layout.item_music_top, parent, false));
            case TYPE_MIDDLE:
                return new MiddleViewHolder(inflater.inflate(R.layout.item_music_middle, parent, false));
            case TYPE_BOTTOM:
                return new BottomViewHolder(inflater.inflate(R.layout.item_music_bottom, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {
            setTopItemValues((TopViewHolder) holder, position);
        } else if (holder instanceof BottomViewHolder) {
            setBottomItemValues((BottomViewHolder) holder, position);
        }else {
            setMiddleValues((MiddleViewHolder)holder,position);
        }
    }

    private void setMiddleValues(final MiddleViewHolder holder, final int position) {
         holder.expand_img.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mOnItemClickListenr.onMiddleClick(holder.expand_img,position);
             }
         });
        holder.expand_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onMiddleClick(holder.expand_menu,position);
            }
        });

    }

    private void setBottomItemValues(BottomViewHolder holder, int position) {
        MusicMyItem item = mList.get(position - 1);
        int count = item.getCount();
        Drawable res = item.getImageRes();
        String title = item.getTitle();
        holder.mTvTitle.setText(title);
        holder.mTvCount.setText(count+"首");
        holder.mIvPhoto.setImageDrawable(res);
        holder.mIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mOnItemClickListenr.onItemClick();\

            }
        });

    }

    private void setTopItemValues(TopViewHolder holder, final int position) {
        MusicMyItem item = mList.get(position);
        int count = item.getCount();
        Drawable res = item.getImageRes();
        String title = item.getTitle();
        holder.mTvCount.setText("("+count+")");
        holder.mTvContent.setText(title);
        holder.mIvPhoto.setImageDrawable(res);
        //Log.d("MusicMyAdapter", "position:" + position);
        holder.mTvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position);
                Log.d("MusicMyAdapter", "position:" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }


    public class TopViewHolder extends RecyclerView.ViewHolder{
        private ImageView mIvPhoto;
        private TextView mTvContent;
        private TextView mTvCount;
        public TopViewHolder(View itemView) {
            super(itemView);
            mIvPhoto = (ImageView) itemView.findViewById(R.id.iv_music_my);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_music_my_content);
            mTvCount = (TextView) itemView.findViewById(R.id.tv_music_my_count);
        }
    }
    public class MiddleViewHolder extends RecyclerView.ViewHolder{
        private ImageView expand_img;
        private ImageView expand_menu;
        private TextView expandtitle;

        public MiddleViewHolder(View itemView) {
            super(itemView);
            expand_img = (ImageView) itemView.findViewById(R.id.iv_expand_img);
            expand_menu = (ImageView) itemView.findViewById(R.id.iv_expand_menu);
            expandtitle = (TextView) itemView.findViewById(R.id.iv_expand_title);
        }
    }
    public class BottomViewHolder extends RecyclerView.ViewHolder{
        private ImageView mIvPhoto;
        private TextView mTvTitle;
        private TextView mTvCount;
        private ImageView mIvMenu;
        public BottomViewHolder(View itemView) {
            super(itemView);
            mIvPhoto = (ImageView) itemView.findViewById(R.id.iv_music_list);
            mIvMenu = (ImageView) itemView.findViewById(R.id.iv_music_list_menu);
            mTvCount = (TextView) itemView.findViewById(R.id.tv_music_list_count);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_music_list_title);
        }
    }

    public interface onItemClickListenr {
        void onItemClick(int position);
        void onMiddleClick(View v,int position);
    }

    public void setOnItemClickListener(onItemClickListenr onItemClickListenr) {
        this.mOnItemClickListenr = onItemClickListenr;
    }

}
