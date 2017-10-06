package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.widget.SuperTextView;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

/**
 * Created by zct11 on 2017/10/5.
 */

public class BookChooseAdapter extends RecyclerView.Adapter<BookChooseAdapter.CommonViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<String> list;
    private onItemClickListenr mOnItemClickListenr;//RecycleView监听事件

    public BookChooseAdapter(Context context,List<String> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public BookChooseAdapter.CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new CommonViewHolder(inflater.inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(BookChooseAdapter.CommonViewHolder holder, final int position) {
        holder.mTitle.setText(list.get(position));
        ViewGroup.LayoutParams params = holder.mTitle.getLayoutParams();
        if (params instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) holder.mTitle.getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }
        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder{
        private SuperTextView mTitle;
        public CommonViewHolder(View itemView) {
            super(itemView);
            mTitle = (SuperTextView) itemView.findViewById(R.id.stv_title);

        }
    }
    public interface onItemClickListenr {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListenr onItemClickListenr) {
        this.mOnItemClickListenr = onItemClickListenr;
    }

}
