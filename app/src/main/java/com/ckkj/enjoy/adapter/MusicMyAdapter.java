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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopViewHolder(inflater.inflate(R.layout.item_music_top, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {
            setTopItemValues((TopViewHolder) holder, position);
        }
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
        return mList.size();
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


    public interface onItemClickListenr {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListenr onItemClickListenr) {
        this.mOnItemClickListenr = onItemClickListenr;
    }

}
