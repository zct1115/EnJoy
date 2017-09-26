package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.bean.Movie;
import com.ckkj.enjoy.bean.NewMovie;
import com.ckkj.enjoy.utils.DisplayUtil;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import java.util.List;


public class UpdateMovieAdapter extends RecyclerView.Adapter<UpdateMovieAdapter.MovieViewHolder> {
    private Context mContext;
    private List<NewMovie.SubjectsBean> mData;
    private final LayoutInflater inflater;
    private onItemClickListenr mOnItemClickListenr;

    public UpdateMovieAdapter(Context context, List<NewMovie.SubjectsBean> list) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mData = list;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(inflater.inflate(R.layout.item_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        NewMovie.SubjectsBean info = mData.get(position);
        double score = info.getRating().getAverage();
        holder.mTvRating.setText(score+"");
        String name = info.getTitle();
        holder.mTvMovieName.setText(name);
        String url = info.getImages().getLarge();
        ImageLoaderUtils.display(mContext,holder.mIvPhoto,url);
        if(score>5){
            holder.mIvHot.setVisibility(View.VISIBLE);
        }else{
            holder.mIvHot.setVisibility(View.GONE);
        }
        holder.mRlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position,holder.mIvPhoto);
            }
        });
        int width = DisplayUtil.getScreenWidth(mContext);
        ViewGroup.LayoutParams params = holder.mRlRoot.getLayoutParams();
        params.width = width/2-40;
        holder.mRlRoot.setLayoutParams(params);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvPhoto;
        private RelativeLayout mRlRoot;
        private ImageView mIvHot;
        private TextView mTvRating;
        private TextView mTvMovieName;
        public MovieViewHolder(View itemView) {
            super(itemView);
            mIvHot = (ImageView) itemView.findViewById(R.id.iv_hot);
            mIvPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
            mTvRating  = (TextView) itemView.findViewById(R.id.tv_rating);
            mTvMovieName = (TextView) itemView.findViewById(R.id.tv_name);
            mRlRoot = (RelativeLayout) itemView.findViewById(R.id.rl_root);
        }
    }
    public interface onItemClickListenr{
        void onItemClick(int position, ImageView imageView);
    }
    public void setOnItemClickListenr(onItemClickListenr onItemClickListenr){
        this.mOnItemClickListenr = onItemClickListenr;
    }



}
