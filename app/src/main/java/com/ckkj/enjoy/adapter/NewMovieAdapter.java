package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.bean.ActorDetils;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import java.util.List;


public class NewMovieAdapter extends RecyclerView.Adapter<NewMovieAdapter.ViewHolder> {


    public List<NewMovie.SubjectsBean> data=null;
    private Context mcontext;

    private MovieDetailAdapter.OnItemClickListener OnItemClickListener;


    public NewMovieAdapter(List<NewMovie.SubjectsBean> data, Context context) {
        this.data = data;
        this.mcontext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_content,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(data!=null){
            holder.textView.setText(data.get(position).getOriginal_title());
            ImageLoaderUtils.display(mcontext,holder.imageView,data.get(position).getImages().getLarge());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnItemClickListener.OnItemClick((ImageView) v,position,data.get(position).getId());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        }else {
            return 5;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name);
            imageView=(ImageView) itemView.findViewById(R.id.img);

        }
    }
    public interface OnItemClickListener {
        void  OnItemClick(ImageView view,int position, String viewid);
    }


    public void setOnItemDetilsClickListener(MovieDetailAdapter.OnItemClickListener mOnItemClickListener) {
        this.OnItemClickListener = mOnItemClickListener;
    }

}
