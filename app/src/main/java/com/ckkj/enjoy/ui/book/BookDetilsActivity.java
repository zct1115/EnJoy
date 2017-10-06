package com.ckkj.enjoy.ui.book;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.base.BaseActivityWithoutStatus;
import com.ckkj.enjoy.bean.BookDetailBean;
import com.ckkj.enjoy.ui.book.presenter.BookDetilsPresenter;
import com.ckkj.enjoy.ui.book.view.BookDetilsView;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetilsActivity extends BaseActivityWithoutStatus implements BookDetilsView {


    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.writer)
    TextView writer;
    @BindView(R.id.publicer)
    TextView publicer;
    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.list)
    TextView list;

    private String id;
    private static Context mcontext;
    private static int radius = 25;
    private BookDetilsPresenter presenter;

    @Override
    public void initView() {
        mcontext=this;
        Intent intent=getIntent();
        id=intent.getStringExtra("bookid");
        presenter.getBookdetils(id);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_detils;
    }

    @Override
    public void initPresenter() {
        presenter=new BookDetilsPresenter(this);
    }

    @Override
    public void getBookdetils(final BookDetailBean bookDetailBean) {

        if(bookDetailBean!=null){
            title.setText(bookDetailBean.getTitle());
            ImageLoaderUtils.display(this,ivPhoto,bookDetailBean.getImages().getLarge());
            new PathAsyncTask(bg).execute(bookDetailBean.getImages().getLarge());
            writer.setText(bookDetailBean.getAuthor().toString());
            content.setText(bookDetailBean.getSummary());
            author.setText(bookDetailBean.getAuthor_intro());
            tvRating.setText(bookDetailBean.getRating().getAverage());
            list.setText(bookDetailBean.getCatalog());
            publicer.setText(bookDetailBean.getPublisher());
            data.setText(bookDetailBean.getPubdate());
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
                            toolbarLayout.setTitle(bookDetailBean.getTitle());
                            AppContent.state = AppContent.CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                        }
                    }
                }
            });
        }

    }

    private static class PathAsyncTask extends AsyncTask<String, Void, String> {
        private ImageView mImageView;

        public PathAsyncTask(ImageView view) {
            this.mImageView = view;
        }

        private String mPath;
        private FileInputStream mIs;

        @Override
        protected String doInBackground(String... params) {
            FutureTarget<File> future = Glide.with(AppApplication.getAppContext())
                    .load(params[0])
                    .downloadOnly(100, 100);
            try {
                File cacheFile = future.get();
                mPath = cacheFile.getAbsolutePath();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return mPath;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                mIs = new FileInputStream(s);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(mIs);
            applyBlur(bitmap, mImageView);

            try {
                mIs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private static void applyBlur(Bitmap bgBitmap, ImageView view) {
        //处理得到模糊效果的图
        RenderScript renderScript = RenderScript.create(AppApplication.getAppContext());
        // Allocate memory for Renderscript to work with
        final Allocation input = Allocation.createFromBitmap(renderScript, bgBitmap);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());
        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);
        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);
        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);
        // Copy the output to the blurred bitmap
        output.copyTo(bgBitmap);
        renderScript.destroy();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(mcontext.getResources(), bgBitmap);
        view.setBackground(bitmapDrawable);

    }

}
