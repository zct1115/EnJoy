package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ckkj.enjoy.R;

/**
 * Created by zct11 on 2017/10/6.
 */

public class OnLoadFailture extends RecyclerView.Adapter<OnLoadFailture.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public OnLoadFailture(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.layout_irecyclerview_load_more_footer_error_view,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         if(holder instanceof ViewHolder){
             setFailture((ViewHolder)holder,position);
         }
    }

    private void setFailture(ViewHolder holder, int position) {
          holder.lin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                onItemClickListener.OnfailtureRestartClick();
              }
          });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout lin;

        public ViewHolder(View itemView) {
            super(itemView);
            lin= (LinearLayout) itemView.findViewById(R.id.tvError);
        }
    }
    public interface OnItemClickListener {
        void  OnfailtureRestartClick();
    }


    public void setOnfailtureClickListener(OnItemClickListener mOnItemClickListener) {
        this.onItemClickListener = mOnItemClickListener;
    }

}
