package com.ckkj.enjoy.ui.music.model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.ui.music.DownloadManagerActivity;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload2.RxDownload;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by XISEVEN on 2017/6/12.
 */

public class DownLoadModel {
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private int NotificationId = 0;

    public DownLoadModel(int notificationId) {
        this.NotificationId = notificationId;
        Intent intent = new Intent(AppApplication.getAppContext(), DownloadManagerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(AppApplication.getAppContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mNotificationManager = (NotificationManager) AppApplication.getAppContext().getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(AppApplication.getAppContext());
        mBuilder.setContentTitle("电子文件下载")//设置通知栏标题
                .setContentIntent(pendingIntent) //设置通知栏点击意图
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setOngoing(true)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                .setSmallIcon(R.mipmap.music);//设置通知小ICON
    }

    /**
     *
     * 后台下载
     * 适合大型文件下载
     * @param url
     * @param name
     */
    public void downLoad(String url, final String name){
        RxDownload.getInstance(AppApplication.getAppContext())
                .serviceDownload(url, name)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Log.d("DownLoadModel", "开始下载");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("DownLoadModel", "添加下载任务");
                    }
                });
    }

    /**
     *
     * 前台下载
     * 支持轻量文件下载，不适合大型文件
     * @param url
     * @param tableName
     * @param name
     */
   /* public void downLoad(String url, final String tableName, final String name) {
        RxDownload.getInstance(App.getContext())
                .defaultSavePath(PathUtils.getPath(tableName)) //设置默认的下载路径
                .maxThread(3)                     //设置最大线程
                .maxRetryCount(3)                 //设置下载失败重试次数
                .maxDownloadNumber(5)             //Service同时下载数量
                .download(url, name)                       //只传url即可
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DownloadStatus>() {
                    @Override
                    public void accept(DownloadStatus status) throws Exception {
                        //DownloadStatus为下载进度
                        Log.d("download", "accept: " + status.getPercent());
                        mBuilder.setContentTitle(name);
                        mBuilder.setContentText("下载中...");
                        mBuilder.setProgress((int) status.getTotalSize(), (int) status.getDownloadSize(), false);
                        mNotificationManager.notify(NotificationId, mBuilder.build());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //下载失败
                        Log.d("download", "accept: ");
                        mBuilder.setContentTitle(name);
                        mBuilder.setContentText("下载失败");
                        mBuilder.setProgress(0, 0, false);
                        mBuilder.setOngoing(false);
                        mNotificationManager.notify(NotificationId, mBuilder.build());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //下载成功
                        Log.d("download", "run: ");
                        mBuilder.setContentTitle(name);
                        mBuilder.setContentText("下载成功");
                        mBuilder.setProgress(0, 0, false);
                        mBuilder.setOngoing(false);
                        mNotificationManager.notify(NotificationId, mBuilder.build());
                        Log.d("download", "run: 1111");

                    }
                });
    }*/



}

