package com.ckkj.enjoy.ui.music.fragment;

import android.os.Bundle;
import android.widget.ImageView;


import com.ckkj.enjoy.R;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import butterknife.BindView;

/**
 * Created by lvr on 2017/5/2.
 */

public class RoundFragment extends BaseFragment {
    @BindView(R.id.civ_photo)
    ImageView mCivPhoto;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_round;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            String picUrl = bundle.getString("picUrl");
            ImageLoaderUtils.displayRound(getActivity(),mCivPhoto,picUrl);
        }
    }

}
