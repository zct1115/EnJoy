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
import com.ckkj.enjoy.bean.MovieDetils;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import java.util.List;



public class ActorWorkDetailAdapter extends RecyclerView.Adapter<ActorWorkDetailAdapter.ViewHolder> {


    public List<ActorDetils.WorksBean> data=null;
    private Context mcontext;



    public ActorWorkDetailAdapter(List<ActorDetils.WorksBean> data, Context context) {
        this.data = data;
        this.mcontext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor_content,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(data.get(position).getSubject().getTitle());
        ImageLoaderUtils.display(mcontext,holder.imageView,data.get(position).getSubject().getImages().getLarge());
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
            textView = (TextView) itemView.findViewById(R.id.name);
            imageView=(ImageView) itemView.findViewById(R.id.actor_img);

        }
    }

}
