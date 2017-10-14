package com.ckkj.enjoy.widget;

/**
 * Created by Administrator on 2017/10/13.
 */

import java.io.IOException;
import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements
        SurfaceHolder.Callback {
    private static final String TAG = "Kintai";

    private static SurfaceHolder holder;
    private Camera mCamera;

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "new View ...");

        holder = getHolder();//后面会用到！
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        Log.i(TAG, "surfaceCreated...");
        if (mCamera == null) {
            mCamera = Camera.open();//开启相机，可以放参数 0 或 1，分别代表前置、后置摄像头，默认为 0
            try {
                mCamera.setPreviewDisplay(holder);//整个程序的核心，相机预览的内容放在 holder
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        Log.i(TAG, "surfaceChanged...");
        mCamera.startPreview();//该方法只有相机开启后才能调用
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        Log.i(TAG, "surfaceChanged...");
        if (mCamera != null) {
            mCamera.release();//释放相机资源
            mCamera = null;
        }
    }

}