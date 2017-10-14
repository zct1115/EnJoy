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


/**
 * 该类为电影演员作品的适配器用于显示演员作品
 */
public class ActorWorkDetailAdapter extends RecyclerView.Adapter<ActorWorkDetailAdapter.ViewHolder> {

    //存放数据的list
    public List<ActorDetils.WorksBean> data = null;
    //上下文
    private Context mcontext;


    /**
     * 构造器
     *
     * @param data    传来的数据
     * @param context 上下文
     */
    public ActorWorkDetailAdapter(List<ActorDetils.WorksBean> data, Context context) {
        this.data = data;
        this.mcontext = context;
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return 参数一：ViewGroup parent，是指RecycleView的布局    参数二：int viewType，是指Item的属性，该属性是在public int getItemViewType(int position)中进行设置获取的。
     */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor_content, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    /**
     * 绑定试图
     * holder 参数是为了获取对应的控件
     * position 每一项
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(data.get(position).getSubject().getTitle());
        ImageLoaderUtils.display(mcontext, holder.imageView, data.get(position).getSubject().getImages().getLarge());
    }

    /**
     *返回你的数据列表的长度
     * @return
     */
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
            imageView = (ImageView) itemView.findViewById(R.id.actor_img);

        }
    }

}
