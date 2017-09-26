package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.utils.Bimp;
import com.ckkj.enjoy.utils.DisplayUtil;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import java.util.List;



public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.ViewHolder> {


    public List<MovieDetils.CastsBean> data=null;
    private Context mcontext;
    private OnItemClickListener OnItemClickListener;



    public MovieDetailAdapter(List<MovieDetils.CastsBean> data, Context context) {
        this.data = data;
        this.mcontext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(data.get(position).getName());
        ImageLoaderUtils.display(mcontext,holder.imageView,data.get(position).getAvatars().getLarge());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnItemClickListener.OnItemClick(holder.imageView,position,data.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.actor_name);
            imageView=(ImageView) itemView.findViewById(R.id.actor_img);

        }
    }

    public interface OnItemClickListener {
        void  OnItemClick(ImageView view,int position, String viewid);
    }


    public void setOnItemDetilsClickListener(OnItemClickListener  mOnItemClickListener) {
        this.OnItemClickListener = mOnItemClickListener;
    }
}
