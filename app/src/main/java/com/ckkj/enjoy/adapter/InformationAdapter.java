package com.ckkj.enjoy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.bean.InformationBean;
import com.ckkj.enjoy.database.login.Login;
import com.ckkj.enjoy.utils.BitmapUtils;
import com.ckkj.enjoy.utils.ImageLoaderUtils;

import java.io.File;
import java.util.List;

/**
 * 个人信息适配器
 * Created by Administrator on 2017/10/13.
 */

public class InformationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    //上下文
    private final Context context;
    //监听事件
    private onItemClickListenr mOnItemClickListenr;
    //布局反射器
    private final LayoutInflater inflater;
    //个人信息list
    private List<InformationBean> list;
    //头像布局 特殊布局
    private int TYPE_SPECIAL = 0;
    //文本布局
    private int TYPE_COMMON =1;
    //高度
    private int mHeight;
    //宽度
    private int mWidth;


    /**
     *
     * 构造方法
     * @param context
     * @param list
     */
    public InformationAdapter(Context context, List<InformationBean> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;

    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_SPECIAL;
        }else {
            return TYPE_COMMON;
        }
    }

    /**
     * 创造ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //判断是否为头像或者是文本
        if(viewType == TYPE_SPECIAL){
            return new SpecialViewHolder(inflater.inflate(R.layout.item_information_special, parent, false));
        }else if(viewType==TYPE_COMMON){
            return new CommonViewHolder(inflater.inflate(R.layout.item_information_common, parent, false));
        }
        return null;

    }

    /**
     * 绑定Viewholder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        Login login=new Login(AppApplication.getAppContext());


        if (holder instanceof SpecialViewHolder){
            InformationBean bean = list.get(position);
            ((SpecialViewHolder) holder).mTextView.setText(bean.getTitle());
            if(bean.isSet()){
                String content = bean.getContent();
                if(content.startsWith("http")){
                    //网络图片
                    ImageLoaderUtils.displayRound(context, ((SpecialViewHolder) holder).mImageView,content);
                }else{
                    final File file = new File(content);
                    /*oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件布局要在onResume回调后完成。所以现在需要使用getViewTreeObserver().addOnGlobalLayoutListener()来获得宽度或者高度。这是获得一个view的宽度和高度的方法之一。*/
                    ((SpecialViewHolder) holder).mImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            /*OnGlobalLayoutListener 是ViewTreeObserver的内部类，当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得。*/
                            ((SpecialViewHolder) holder).mImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            mWidth = ((SpecialViewHolder) holder).mImageView.getMeasuredWidth();
                            mHeight = ((SpecialViewHolder) holder).mImageView.getMeasuredHeight();
                            Bitmap bitmap = BitmapUtils.decodeBitmapFromFile(file, mWidth, mHeight);
                            if (bitmap != null) {
                                //检查是否有被旋转，并进行纠正
                                int degree = BitmapUtils.getBitmapDegree(file.getAbsolutePath());
                                if (degree != 0) {
                                    bitmap = BitmapUtils.rotateBitmapByDegree(bitmap, degree);
                                }
                                ((SpecialViewHolder) holder).mImageView.setImageBitmap(bitmap);
                            }

                        }
                    });
                }
            }else{
                if(login.getlist().size()==0){
                    ((SpecialViewHolder) holder).mImageView.setImageResource(R.drawable.nav_photo);
                }else {
                    // TODO: 2017/10/15 此处有问题 
                    final File file = new File(login.getlist().get(0).getUser_photo_url());
                    /*oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件布局要在onResume回调后完成。所以现在需要使用getViewTreeObserver().addOnGlobalLayoutListener()来获得宽度或者高度。这是获得一个view的宽度和高度的方法之一。*/
                    ((SpecialViewHolder) holder).mImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            /*OnGlobalLayoutListener 是ViewTreeObserver的内部类，当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得。*/
                            ((SpecialViewHolder) holder).mImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            mWidth = ((SpecialViewHolder) holder).mImageView.getMeasuredWidth();
                            mHeight = ((SpecialViewHolder) holder).mImageView.getMeasuredHeight();
                            Bitmap bitmap = BitmapUtils.decodeBitmapFromFile(file, mWidth, mHeight);
                            if (bitmap != null) {
                                //检查是否有被旋转，并进行纠正
                                int degree = BitmapUtils.getBitmapDegree(file.getAbsolutePath());
                                if (degree != 0) {
                                    bitmap = BitmapUtils.rotateBitmapByDegree(bitmap, degree);
                                }
                                ((SpecialViewHolder) holder).mImageView.setImageBitmap(bitmap);
                            }

                        }
                    });
                }

            }
            //设置头像监听
            ((SpecialViewHolder) holder).mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListenr.onItemClick(position);
                }
            });

        }else{
            /*文本信息设置*/




            InformationBean bean = list.get(position);
            ((CommonViewHolder) holder).mTitle.setText(bean.getTitle());
            if(bean.isSet()){
                ((CommonViewHolder) holder).mContent.setText(bean.getContent());
                ((CommonViewHolder) holder).mContent.setTextColor(context.getResources().getColor(R.color.text_color));
            }else{
                if(login.getlist().size()==0){
                    ((CommonViewHolder) holder).mContent.setText("未设置");
                    ((CommonViewHolder) holder).mContent.setTextColor(context.getResources().getColor(R.color.time_line));
                }else {
                    switch (position){
                        case  1:
                            ((CommonViewHolder) holder).mContent.setText(login.getlist().get(0).getUsername());
                            ((CommonViewHolder) holder).mContent.setTextColor(context.getResources().getColor(R.color.text_color));
                            break;
                        case  2:
                            ((CommonViewHolder) holder).mContent.setText(login.getlist().get(0).getAge());
                            ((CommonViewHolder) holder).mContent.setTextColor(context.getResources().getColor(R.color.text_color));
                            break;
                        case  3:
                            ((CommonViewHolder) holder).mContent.setText(login.getlist().get(0).getSex());
                            ((CommonViewHolder) holder).mContent.setTextColor(context.getResources().getColor(R.color.text_color));
                            break;
                        case  4:
                            ((CommonViewHolder) holder).mContent.setText(login.getlist().get(0).getMovie_preference());
                            ((CommonViewHolder) holder).mContent.setTextColor(context.getResources().getColor(R.color.text_color));
                            break;
                        case  5:
                            ((CommonViewHolder) holder).mContent.setText(login.getlist().get(0).getMusic_preference());
                            ((CommonViewHolder) holder).mContent.setTextColor(context.getResources().getColor(R.color.text_color));
                            break;
                    }

                }
            }
            //文本信息监听
            ((CommonViewHolder) holder).mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListenr.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SpecialViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
        private ImageView mImageView;

        public SpecialViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_content);


        }
    }
    public class CommonViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mContent;
        private RelativeLayout mLayout;
        public CommonViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mContent = (TextView) itemView.findViewById(R.id.tv_content);
            mLayout = (RelativeLayout) itemView.findViewById(R.id.relativelayout);

        }
    }

    /**
     * 监听接口
     */
    public interface onItemClickListenr{
        void onItemClick(int position);
    }
    public void setOnItemClickListenr(onItemClickListenr onItemClickListenr){
        this.mOnItemClickListenr = onItemClickListenr;
    }
}
