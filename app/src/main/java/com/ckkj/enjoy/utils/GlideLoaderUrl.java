package com.ckkj.enjoy.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by zct on 2017/4/24.
 */

public class GlideLoaderUrl extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageLoaderUtils.display(context,imageView,(String) path);
    }

}
