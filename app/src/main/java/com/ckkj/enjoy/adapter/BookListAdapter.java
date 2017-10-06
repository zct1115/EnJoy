package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.bean.BookBean;
import com.ckkj.enjoy.bean.OtherMovie;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import java.util.List;


public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {


    public List<BookBean.BooksBean> data=null;
    private Context mcontext;

    private OnItemClickListener OnItemClickListener;


    public BookListAdapter(List<BookBean.BooksBean> data, Context context) {
        this.data = data;
        this.mcontext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(data.get(position).getTitle());
        holder.tag.setText(data.get(position).getRating().getAverage());
        ImageLoaderUtils.display(mcontext,holder.imageView,data.get(position).getImages().getLarge());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnItemClickListener.OnItemClick((ImageView) v,position,data.get(position).getId());
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
        public TextView tag;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name);
            imageView=(ImageView) itemView.findViewById(R.id.bookimg);
            tag = (TextView) itemView.findViewById(R.id.tag);

        }
    }
    public interface OnItemClickListener {
        void  OnItemClick(ImageView view, int position, String viewid);
    }


    public void setOnBookClickListener(OnItemClickListener mOnItemClickListener) {
        this.OnItemClickListener = mOnItemClickListener;
    }

}
