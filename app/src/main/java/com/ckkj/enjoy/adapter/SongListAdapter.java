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
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.bean.WrapperSongListInfo;
import com.ckkj.enjoy.utils.DisplayUtil;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by zct11 on 2017/9/26.
 */

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongListViewHolder> {

    private Context mcontext;
    private List<WrapperSongListInfo.SongListInfo> songListInfos;
    private final LayoutInflater inflater;

    public SongListAdapter(Context context, List<WrapperSongListInfo.SongListInfo> songListInfos) {
        this.mcontext = context;
        this.songListInfos = songListInfos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public SongListAdapter.SongListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongListViewHolder(inflater.inflate(R.layout.item_songlist, parent, false));
    }

    @Override
    public void onBindViewHolder(SongListAdapter.SongListViewHolder holder, int position) {
        final WrapperSongListInfo.SongListInfo info = songListInfos.get(position);
        int count = Integer.parseInt(info.getListenum());
        String name = info.getTitle();
        String url = info.getPic_300();
        if (count > 10000) {
            count = count / 10000;
            holder.mTvCount.setText(count+"万");
        } else {
            holder.mTvCount.setText(info.getListenum());
        }
        holder.mTvName.setText(name);
        ImageLoaderUtils.display(mcontext,holder.mIvPhoto,url);
        int width = DisplayUtil.getScreenWidth(mcontext);
        ViewGroup.LayoutParams params = holder.mRlRoot.getLayoutParams();
        params.width = width/2-40;
        holder.mRlRoot.setLayoutParams(params);
        holder.mRlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return songListInfos.size()-2;
    }

    public class SongListViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvPhoto;
        private TextView mTvName;
        private RelativeLayout mRlRoot;
        private TextView mTvCount;

        public SongListViewHolder(View itemView) {
            super(itemView);
            mIvPhoto = (ImageView) itemView.findViewById(R.id.iv_songlist_photo);
            mTvCount = (TextView) itemView.findViewById(R.id.tv_songlist_count);
            mTvName = (TextView) itemView.findViewById(R.id.tv_songlist_name);
            mRlRoot = (RelativeLayout) itemView.findViewById(R.id.rl_root);
        }
    }
}
