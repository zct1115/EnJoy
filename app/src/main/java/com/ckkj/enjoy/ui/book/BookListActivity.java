package com.ckkj.enjoy.ui.book;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.BookListAdapter;
import com.ckkj.enjoy.adapter.OnLoadFailture;
import com.ckkj.enjoy.anims.LandingAnimator;
import com.ckkj.enjoy.anims.ScaleInAnimationAdapter;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.BookBean;
import com.ckkj.enjoy.client.NetworkUtil;
import com.ckkj.enjoy.ui.book.presenter.BookPresenter;
import com.ckkj.enjoy.ui.book.view.BookListView;
import com.ckkj.enjoy.ui.movie.MovieDetilsActivity;
import com.ckkj.enjoy.widget.LoadMoreFooterView;
import com.ckkj.enjoy.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListActivity extends BaseActivity implements BookListView, BookListAdapter.OnItemClickListener,OnLoadFailture.OnItemClickListener,OnLoadMoreListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycle)
    IRecyclerView recycle;

    private BookPresenter bookPresenter;
    private int start = 0;
    private int count = 20;
    private BookListAdapter adapter;
    private List<BookBean.BooksBean> data = new ArrayList<>();
    private Context context;
    private String bookname;
    private LoadMoreFooterView mFooterView;

    @Override
    public void initView() {
        context = this;

        Intent intent = getIntent();
        bookname = intent.getStringExtra("book");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(bookname);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LoadingDialog.showDialogForLoading(this);

        setRecycle(bookname);

    }

    private void setRecycle(String book) {

        if(!NetworkUtil.isNetworkAvailable(this)){
            OnLoadFailture failture=new OnLoadFailture(context);
            RecyclerView.LayoutManager manager=new LinearLayoutManager(context);
            recycle.setLayoutManager(manager);
            failture.setOnfailtureClickListener(this);
            recycle.setAdapter(failture);
        }else {
            adapter = new BookListAdapter(data, context);
            GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            recycle.setLayoutManager(manager);
            recycle.setItemAnimator(new LandingAnimator());
            recycle.setOnLoadMoreListener(this);
            adapter.setOnBookClickListener(this);
           /*设置适配器*/
            recycle.setAdapter(new ScaleInAnimationAdapter(adapter));
            mFooterView=(LoadMoreFooterView) recycle.getLoadMoreFooterView();
            bookPresenter.getBooklist(book, start, count);
        }

    }

    @Override
    public void initPresenter() {
        bookPresenter = new BookPresenter(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_book_list;
    }

    @Override
    public void getBooklist(List<BookBean.BooksBean> bookBean) {
        if (bookBean != null) {
            if(start==0){
                data.clear();
                data.addAll(bookBean);
                adapter.notifyDataSetChanged();
            }else {
                if(data.size()<count){
                    count=data.size();
                }
                data.addAll(bookBean);
                adapter.notifyDataSetChanged();
            }

        }else {
            start--;
            mFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
        }
        LoadingDialog.cancelDialogForLoading();
    }


    @Override
    public void OnItemClick(ImageView view, int position, String viewid) {
        Intent intent = new Intent(this, BookDetilsActivity.class);
        intent.putExtra("bookid", viewid);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation((Activity) mContext, view, AppContent.TRANSITION_IMAGE_MOVIE);
            mContext.startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());
        }
    }


    @Override
    public void OnfailtureRestartClick() {
        setRecycle(bookname);
    }

    @Override
    public void onLoadMore() {
        if(count<20){
            //moviePresenter.requestHotMovie(count,start);
            mFooterView.setStatus(LoadMoreFooterView.Status.THE_END);

        }else {
            start=start+count;
            bookPresenter.getBooklist(bookname, start, count);
            mFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
        }
    }
}
