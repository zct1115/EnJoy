package com.ckkj.enjoy.ui.movie;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.ActorWorkDetailAdapter;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.ActorDetils;
import com.ckkj.enjoy.ui.movie.presenter.ActorPresenter;
import com.ckkj.enjoy.ui.movie.presenter.ActorPresenterImpl;
import com.ckkj.enjoy.ui.movie.view.ActorView;
import com.ckkj.enjoy.utils.ImageLoaderUtils;
import com.ckkj.enjoy.widget.LoadingDialog;

import butterknife.BindView;

/**
 * Created by HiWin10 on 2017/9/21 0021.
 */

public class ActorActivity extends BaseActivity implements ActorView {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.E_name)
    TextView EName;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.county)
    TextView county;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.xrv_work)
    RecyclerView xrvWork;
    @BindView(R.id.callname)
    TextView callname;
    @BindView(R.id.works)
    TextView works;
    private ActorPresenter actorPresenter;

    @Override
    public void initView() {
        Intent intent = getIntent();
        String actor = intent.getStringExtra("viewid");
        LoadingDialog.showDialogForLoading(this);
        actorPresenter.getActor(actor);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initPresenter() {
        actorPresenter = new ActorPresenterImpl(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_actor_detils;
    }

    @Override
    public void getActor(final ActorDetils actorDetils) {

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (AppContent.state != AppContent.CollapsingToolbarLayoutState.EXPANDED) {
                        AppContent.state = AppContent.CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        toolbarLayout.setTitle("");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (AppContent.state != AppContent.CollapsingToolbarLayoutState.COLLAPSED) {
                        toolbarLayout.setTitle(actorDetils.getName());
                        AppContent.state = AppContent.CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                }
            }
        });
        name.setText(actorDetils.getName());
        ImageLoaderUtils.display(this, ivPhoto, actorDetils.getAvatars().getLarge());
        EName.setText(actorDetils.getName_en());
        sex.setText(actorDetils.getGender());
        if (actorDetils.getAka().size() == 0) {
            callname.setText("无");
        } else {
            callname.setText(actorDetils.getAka().toString());
        }

        county.setText(actorDetils.getBorn_place());
        works.setText("最受欢迎的作品("+actorDetils.getWorks().size()+")");

        LinearLayoutManager manager = new LinearLayoutManager(ActorActivity.this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        /*设置个人作品*/
        xrvWork.setLayoutManager(manager);
        xrvWork.setItemAnimator(new DefaultItemAnimator());
        ActorWorkDetailAdapter actorWorkDetailAdapter = new ActorWorkDetailAdapter(actorDetils.getWorks(), ActorActivity.this);
        xrvWork.setAdapter(actorWorkDetailAdapter);
        LoadingDialog.cancelDialogForLoading();

    }


}
