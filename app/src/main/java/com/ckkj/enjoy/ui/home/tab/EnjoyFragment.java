package com.ckkj.enjoy.ui.home.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.BookChooseAdapter;
import com.ckkj.enjoy.base.BaseFragment;
import com.ckkj.enjoy.ui.book.BookListActivity;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HiWin10 on 2017/9/23 0023.
 */

public class EnjoyFragment extends BaseFragment implements BookChooseAdapter.onItemClickListenr{


    @BindView(R.id.xrv_enjoy)
    RecyclerView xrvEnjoy;
    private String[] mTypes = {"文学", "侦探", "传记", "悬疑", "文化", "艺术", "科幻", "管理", "经济","情感","政治", "养生",
            "童书", "计算机", "英语", "考试", "旅游", "美食","人生哲学" ,"励志","法律","营销","外国小说","社会科学","投资"};
    private BookChooseAdapter adapter;
    private Context context;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_enjoy;
    }

    @Override
    protected void initView() {
        context=getActivity();
        setRecyclerView();
    }

    private void setRecyclerView() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(mTypes));
        adapter=new BookChooseAdapter(context,list);
        adapter.setOnItemClickListener(this);
        FlexboxLayoutManager manager = new FlexboxLayoutManager();
        //设置主轴排列方式
        manager.setFlexDirection(FlexDirection.ROW);
        //设置是否换行
        manager.setFlexWrap(FlexWrap.WRAP);

        xrvEnjoy.setLayoutManager(manager);
        xrvEnjoy.setItemAnimator(new DefaultItemAnimator());
        xrvEnjoy.setAdapter(adapter);
    }


    @Override
    public void onItemClick(int position) {
        String book=mTypes[position];
        Intent intent=new Intent(context,BookListActivity.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }
}
