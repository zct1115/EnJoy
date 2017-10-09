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
    //图书的数量从0开始
    private int start = 0;
    //每次获取图书的数量 50
    private int count = 50;
    //图书列表的适配器
    private BookListAdapter adapter;
    //List存储图书信息
    private List<BookBean.BooksBean> data = new ArrayList<>();
    //上下文Context
    private Context context;
    //图书关键字用于保存图书类型
    private String bookname;
    //RecycleView底部加载视图
    private LoadMoreFooterView mFooterView;

    @Override
    public void initView() {
        //初始化
        context = this;
        //通过Intent 获取传值
        Intent intent = getIntent();
        bookname = intent.getStringExtra("book");
        //设置toolbar字体颜色
        toolbar.setTitleTextColor(Color.WHITE);
        //设置toolbar标题
        toolbar.setTitle(bookname);
        //设置toolbar 回退键触发的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //加载进度条用用于网络加载图书数据
        LoadingDialog.showDialogForLoading(this);
        //设置图书适配器
        setRecycle(bookname);

    }

    private void setRecycle(String book) {
        //判断是否当前有没有网络
        if(!NetworkUtil.isNetworkAvailable(this)){
            //如果没有网络，加载网络失败的视图
            //初始化网络加载失败的适配器
            OnLoadFailture failture=new OnLoadFailture(context);
            //设置网路加载失败的布局
            RecyclerView.LayoutManager manager=new LinearLayoutManager(context);
            recycle.setLayoutManager(manager);
            //设置网络加载失败点击事件
            failture.setOnfailtureClickListener(this);
            //设置适配器
            recycle.setAdapter(failture);
        }else {
            //如果有网络
            //初始化图书列表适配器  data为图书数据
            adapter = new BookListAdapter(data, context);
            //设置图书列表的布局 这里采用GridLayoutManager 网格布局 第一个参数为当前上下文，第二个参数为列数，第三个为布局方向（垂直或者水平），第四个参数默认false
            GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            recycle.setLayoutManager(manager);
            //设置每一项动画效果
            recycle.setItemAnimator(new LandingAnimator());
            //设置加载更多监听时间
            recycle.setOnLoadMoreListener(this);
            //设置适配器每一项点击事件
            adapter.setOnBookClickListener(this);
           /*设置适配器*/
            recycle.setAdapter(new ScaleInAnimationAdapter(adapter));
            //初始化底部加载视图
            mFooterView=(LoadMoreFooterView) recycle.getLoadMoreFooterView();
            //加载图书列表数据
            bookPresenter.getBooklist(book, start, count);
        }

    }

    @Override
    public void initPresenter() {
        //初始化P
        bookPresenter = new BookPresenter(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_book_list;
    }

    /**
     * 获取图书信息成功时调用的方法
     * @param bookBean
     */
    @Override
    public void getBooklist(List<BookBean.BooksBean> bookBean) {
        //判定当前数据是否为空

        /*我的思路是判断当前的数据是否从start为0开始，如果不为0的话，判断当前获取的数量是否等于定义的获取数量count，
        如果当前获取的数量小于定义的数量的话，就把count数量设置为当前获取的数量*/
        if (bookBean != null) {
            //判定当前的图书开始是否从0开始
            if(start==0){
                //清空数据
                data.clear();
                //添加数据
                data.addAll(bookBean);
                //适配器通知数据改变
                adapter.notifyDataSetChanged();
            }else {
                //如果当前获取的数量小于定义的数量的话
                if(data.size()<count){
                    //count数量设置为当前获取的数量
                    count=data.size();
                }
                //添加数据
                data.addAll(bookBean);
                //适配器通知数据改变
                adapter.notifyDataSetChanged();
            }

        }else {
            // TODO: 2017/10/9 没有数据的时候设置布局
            start--;
            mFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
        }
        //进度条结束
        LoadingDialog.cancelDialogForLoading();
    }


    /**
     * 图书列表适配器的每一项点击事件
     * RecycleView没有自带的点击事件需要自己设计通过接口的方式实现
     * @param view  当前图书图片
     * @param position  一项
     * @param viewid  图书的id
     */
    @Override
    public void OnItemClick(ImageView view, int position, String viewid) {
        //Intent 跳转
        Intent intent = new Intent(this, BookDetilsActivity.class);
        //传入参数bookid
        intent.putExtra("bookid", viewid);
        //判定当前的sdk是否大于Android 6.0
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


    /**
     * 点击网络失败刷新功能
     */
    @Override
    public void OnfailtureRestartClick() {
        setRecycle(bookname);
    }

    /**
     * 加载更多的设置
     */
    @Override
    public void onLoadMore() {
        /*
        * 我的思路是如果当前的数量小于设置的50，这时数据已经加载完成。所以设置当前没有更多是视图
        * 如果当前的数量等于设置的50，这时候将start+count 加载图书从start+count 开始比如0开始每次加载20，第二次时从20到40，第三次时从40到60.。。。
        * 显示正在加载视图
        * */
        if(count<50){
            //moviePresenter.requestHotMovie(count,start);
            mFooterView.setStatus(LoadMoreFooterView.Status.THE_END);

        }else {
            start=start+count;
            bookPresenter.getBooklist(bookname, start, count);
            mFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
        }
    }
}
