package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.ckkj.enjoy.R;
import com.ckkj.enjoy.bean.DownloadController;
import com.ckkj.enjoy.bean.DownloadItem;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadStatus;
import zlc.season.rxdownload2.function.Utils;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static zlc.season.rxdownload2.function.Utils.dispose;
import static zlc.season.rxdownload2.function.Utils.empty;
import static zlc.season.rxdownload2.function.Utils.log;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/28
 * Time: 09:37
 * FIXME
 */
public class DownloadViewHolder extends AbstractViewHolder<DownloadItem> {
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.percent)
    TextView mPercent;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.size)
    TextView mSize;
    @BindView(R.id.status)
    TextView mStatusText;
    @BindView(R.id.action)
    Button mActionButton;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.more)
    Button mMore;

    private AbstractAdapter mAdapter;
    private DownloadController mDownloadController;

    private Context mContext;
    private DownloadItem data;

    private RxDownload mRxDownload;
    private int flag;

    public DownloadViewHolder(ViewGroup parent, AbstractAdapter adapter) {
        super(parent, R.layout.download_manager_item);
        ButterKnife.bind(this, itemView);
        this.mAdapter = adapter;
        mContext = parent.getContext();

        mRxDownload = RxDownload.getInstance(mContext);

        mDownloadController = new DownloadController(mStatusText, mActionButton);
    }

    @Override
    public void setData(DownloadItem param) {
        this.data = param;

        String name = empty(param.record.getExtra2()) ? param.record.getSaveName() : param.record.getExtra2();
        mName.setText(name);


        Utils.log(data.record.getUrl());
        data.disposable = mRxDownload.receiveDownloadStatus(data.record.getUrl())
                .subscribe(new Consumer<DownloadEvent>() {
                    @Override
                    public void accept(DownloadEvent downloadEvent) throws Exception {
                        if (flag != downloadEvent.getFlag()) {
                            flag = downloadEvent.getFlag();
                            log(flag + "");
                        }

                        if (downloadEvent.getFlag() == DownloadFlag.FAILED) {
                            Throwable throwable = downloadEvent.getError();
                            Log.w("TAG", throwable);
                        }
                        mDownloadController.setEvent(downloadEvent);
                        updateProgressStatus(downloadEvent.getDownloadStatus());
                    }
                });
    }

    @OnClick({R.id.action, R.id.more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action:
                mDownloadController.handleClick(new DownloadController.Callback() {
                    @Override
                    public void startDownload() {
                        start();
                    }

                    @Override
                    public void pauseDownload() {
                        pause();
                    }

                    @Override
                    public void open() {
                        DownloadViewHolder.this.open();
                    }
                });
                break;
            case R.id.more:
                showPopUpWindow(view);
                break;
        }
    }

    private void updateProgressStatus(DownloadStatus status) {
        mProgress.setIndeterminate(status.isChunked);
        mProgress.setMax((int) status.getTotalSize());
        Log.d("DownloadViewHolder", "status.getTotalSize():" + status.getTotalSize());
        mProgress.setProgress((int) status.getDownloadSize());
        Log.d("DownloadViewHolder下载过程", "status.getDownloadSize():" + status.getDownloadSize());
        mPercent.setText(status.getPercent());
        Log.d("DownloadViewHolder进度", status.getPercent());
        mSize.setText(status.getFormatStatusString());
        Log.d("DownloadViewHolder下载速度", status.getFormatStatusString());

    }

    private void open() {
        File[] files = mRxDownload.getRealFiles(data.record.getUrl());
        if (files != null) {
            Intent intent = openFile(files[0]);
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, "File not exists", Toast.LENGTH_SHORT).show();
        }
    }

    private void start() {
        RxPermissions.getInstance(mContext)
                .request(WRITE_EXTERNAL_STORAGE)
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            throw new RuntimeException("no permission");
                        }
                    }
                })
                .compose(mRxDownload.<Boolean>transformService(data.record.getUrl()))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(mContext, "下载开始", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void pause() {
        mRxDownload.pauseServiceDownload(data.record.getUrl()).subscribe();
    }

    private void delete() {
        dispose(data.disposable);
        mRxDownload.deleteServiceDownload(data.record.getUrl(), true)
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mAdapter.remove(getAdapterPosition());
                    }
                })
                .subscribe();

    }

    private void showPopUpWindow(View view) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(mContext);
        listPopupWindow.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,
                new String[]{"删除"}));
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0) {
                    delete();
                    listPopupWindow.dismiss();
                }
            }
        });
        listPopupWindow.setWidth(300);
        listPopupWindow.setAnchorView(view);
        listPopupWindow.setModal(false);
        listPopupWindow.show();
    }

    public Intent openFile(File file) {

        if (!file.exists()) return null;
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            return getAudioFileIntent(file);
        } else if (end.equals("3gp") || end.equals("mp4")) {
            return getVideoFileIntent(file);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            return getImageFileIntent(file);
        } else if (end.equals("apk")) {
            return getApkFileIntent(file);
        } else if (end.equals("ppt") || end.equals("pptx")) {
            return getPptFileIntent(file);
        } else if (end.equals("xls") || end.equals("xlsx")) {
            return getExcelFileIntent(file);
        } else if (end.equals("doc") || end.equals("docx")) {
            return getWordFileIntent(file);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(file);
        } else if (end.equals("chm")) {
            return getChmFileIntent(file);
        } else if (end.equals("txt")) {
            return getTextFileIntent(file);
        } else {
            return getAllIntent(file);
        }
    }

    //Android获取一个用于打开APK文件的intent
    public Intent getAllIntent(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = FileProvider7.getUriForFile(mContext, file);
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    //Android获取一个用于打开APK文件的intent
    public Intent getApkFileIntent(File file) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = FileProvider7.getUriForFile(mContext, file);

        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    //Android获取一个用于打开VIDEO文件的intent
    public Intent getVideoFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = FileProvider7.getUriForFile(mContext, file);

        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    //Android获取一个用于打开AUDIO文件的intent
    public Intent getAudioFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = FileProvider7.getUriForFile(mContext, file);

        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    //Android获取一个用于打开Html文件的intent
    public Intent getHtmlFileIntent(File file) {

        Uri uri = Uri.parse(file.getAbsolutePath()).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(file.getAbsolutePath()).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    //Android获取一个用于打开图片文件的intent
    public Intent getImageFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(mContext, file);

        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    //Android获取一个用于打开PPT文件的intent
    public Intent getPptFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(mContext, file);

        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    //Android获取一个用于打开Excel文件的intent
    public Intent getExcelFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(mContext, file);

        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    //Android获取一个用于打开Word文件的intent
    public Intent getWordFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(mContext, file);

        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    //Android获取一个用于打开CHM文件的intent
    public Intent getChmFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(mContext, file);

        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    //Android获取一个用于打开文本文件的intent
    public Intent getTextFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(mContext, file);
        intent.setDataAndType(uri, "text/plain");

        return intent;
    }

    //Android获取一个用于打开PDF文件的intent
    public Intent getPdfFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = FileProvider7.getUriForFile(mContext, file);

        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }
}
