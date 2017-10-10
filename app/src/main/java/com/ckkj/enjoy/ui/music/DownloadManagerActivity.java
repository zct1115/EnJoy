package com.ckkj.enjoy.ui.music;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.DownloadAdapter;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.DownloadItem;
import com.ckkj.enjoy.client.NetworkUtil;
import com.ckkj.enjoy.message.Download;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;
import zlc.season.rxdownload2.function.Utils;


public class DownloadManagerActivity extends BaseActivity {

    @BindView(R.id.recycler)
    PracticalRecyclerView mRecycler;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.pause)
    Button pause;
    @BindView(R.id.content_main)
    LinearLayout contentMain;

    private DownloadAdapter mAdapter;
    private RxDownload rxDownload;


    @OnClick({R.id.start, R.id.pause})
    public void onClick(View view) {
        List<DownloadItem> list = mAdapter.getData();
        switch (view.getId()) {
            case R.id.start:
                if (!NetworkUtil.isNetworkAvailable(this)) {
                    Toast.makeText(this, "当前无网络，请检查网络状况", Toast.LENGTH_SHORT).show();
                }
                for (DownloadItem each : list) {
                    rxDownload.serviceDownload(each.record.getUrl())
                            .subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Utils.log(throwable);
                                }
                            });
                }
                break;
            case R.id.pause:
                for (DownloadItem each : list) {
                    rxDownload.pauseServiceDownload(each.record.getUrl())
                            .subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Utils.log(throwable);
                                }
                            });
                }
                break;
        }
    }

    @Override
    public void initView() {
        rxDownload = RxDownload.getInstance(this);

        mAdapter = new DownloadAdapter();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapterWithLoading(mAdapter);
        loadData();
        if (!NetworkUtil.isNetworkAvailable(this)) {
            Toast.makeText(this, "当前无网络，请检查网络状况", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_download_manager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        List<DownloadItem> list = mAdapter.getData();
        for (DownloadItem each : list) {
            Utils.dispose(each.disposable);
        }
    }

    private void loadData() {
        RxDownload.getInstance(this).getTotalDownloadRecords()
                .map(new Function<List<DownloadRecord>, List<DownloadItem>>() {
                    @Override
                    public List<DownloadItem> apply(List<DownloadRecord> downloadRecords) throws Exception {
                        List<DownloadItem> result = new ArrayList<>();
                        for (DownloadRecord each : downloadRecords) {
                            DownloadItem bean = new DownloadItem();
                            bean.record = each;
                            result.add(bean);
                        }
                        return result;
                    }
                })
                .subscribe(new Consumer<List<DownloadItem>>() {
                    @Override
                    public void accept(List<DownloadItem> downloadBeen) throws Exception {
                        mAdapter.addAll(downloadBeen);
                    }
                });
    }

}
