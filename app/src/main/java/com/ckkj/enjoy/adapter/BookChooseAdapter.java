package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckkj.enjoy.R;
import com.ckkj.enjoy.widget.SuperTextView;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

/**
 * 该方法为图书标签类型适配器
 */

public class BookChooseAdapter extends RecyclerView.Adapter<BookChooseAdapter.CommonViewHolder> {
    //上下文
    private final Context context;
    //反射器
    private final LayoutInflater inflater;
    //用于存放图书分类的list
    private List<String> list;
    //RecycleView 监听事件需要自行定义，它本身没有自带监听事件，
    private onItemClickListenr mOnItemClickListenr;//RecycleView监听事件

    /**
     * 构造方法
     * @param context
     * @param list
     */
    public BookChooseAdapter(Context context,List<String> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }
    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return 参数一：ViewGroup parent，是指RecycleView的布局    参数二：int viewType，是指Item的属性，该属性是在public int getItemViewType(int position)中进行设置获取的。
     */

    @Override
    public BookChooseAdapter.CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new CommonViewHolder(inflater.inflate(R.layout.item_book, parent, false));
    }

    /**
     * 绑定试图
     * holder 参数是为了获取对应的控件
     * position 每一项
     */

    @Override
    public void onBindViewHolder(BookChooseAdapter.CommonViewHolder holder, final int position) {
        //设置图书类型
        holder.mTitle.setText(list.get(position));
        //其实这个LayoutParams类是用于child view（子视图） 向 parent view（父视图）传达自己的意愿的一个东西（孩子想变成什么样向其父亲说明）其实子视图父视图可以简单理解成
        ViewGroup.LayoutParams params = holder.mTitle.getLayoutParams();
        //运行时指出对象是否是特定类的一个实例
        if (params instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) holder.mTitle.getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }
        //点击事件
        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder{
        private SuperTextView mTitle;
        public CommonViewHolder(View itemView) {
            super(itemView);
            mTitle = (SuperTextView) itemView.findViewById(R.id.stv_title);

        }
    }

    /**
     * 监听接口
     */
    public interface onItemClickListenr {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListenr onItemClickListenr) {
        this.mOnItemClickListenr = onItemClickListenr;
    }

}
