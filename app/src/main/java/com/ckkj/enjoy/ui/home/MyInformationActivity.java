package com.ckkj.enjoy.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.annotation.NonNull;

import com.aspsine.irecyclerview.IRecyclerView;
import com.ckkj.enjoy.R;
import com.ckkj.enjoy.adapter.InformationAdapter;
import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.base.BaseActivity;
import com.ckkj.enjoy.bean.FavorListBean;
import com.ckkj.enjoy.bean.InformationBean;
import com.ckkj.enjoy.utils.BitmapUtils;
import com.ckkj.enjoy.utils.CapturePhotoHelper;
import com.ckkj.enjoy.utils.FolderManager;
import com.ckkj.enjoy.utils.SPUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 我的个人信息详情
 */

public class MyInformationActivity extends BaseActivity implements InformationAdapter.onItemClickListenr {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.irv_information)
    IRecyclerView irvInformation;

    //请求开启相册
    private static final int REQUEST_PICK_IMAGE = 3;
    //显示图像
    private static final int REQUEST_PICKER_AND_CROP_2 = 4;
    //上下文
    private Context mcontext;
    //用于存放电影标签
    private List<String> favorMovie = new ArrayList<>();
    //用于存放音乐标签
    private List<String> favorMusic = new ArrayList<>();
    //个人信息适配器
    private InformationAdapter mAdapter;
    //用于保存我的个人信息
    private List<InformationBean> mList = new ArrayList<>();
    //监听文本信息是否变化了
    private boolean isTextChange;
    //监听图片信息是否变化了
    private boolean isPhotoChange;
    //选取图片并裁剪
    private final static int REQUEST_PICKER_AND_CROP = 2;
    //用于拍照的类
    private CapturePhotoHelper mCapturePhotoHelper;
    //照片文件
    private File mRestorePhotoFile;
    //文件用于保存拍照后图片
    private File tempFile = new File(Environment.getExternalStorageDirectory(), SPUtils.getSharedStringData(AppApplication.getAppContext(), "userid") + ".jpg");
    //保存文件目录
    private final static String EXTRA_RESTORE_PHOTO = "extra_restore_photo";
    //toast
    private final static String TAG = MyInformationActivity.class.getSimpleName();
    /**
     * 运行时权限申请码
     */
    private final static int RUNTIME_PERMISSION_REQUEST_CODE = 0x1;

    @Override
    public void initView() {
        //Evenbus的注册
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mcontext = this;
        //toolbar点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInforamtion();
                finish();
            }
        });
        //toolbar字体颜色设置
        toolbar.setTitleTextColor(Color.WHITE);
        //toolbar标题设置
        toolbar.setTitle("完善个人信息");
        //设置静态数据
        initdata();
        //设置适配器
        setRecycleView();
    }

    /**
     * 初始化静态数据
     */
    private void initdata() {

        // TODO: 2017/10/14 从后台获取数据
        InformationBean bean = new InformationBean();
        bean.setContent("zct");
        bean.setTitle("头像");
        InformationBean bean1 = new InformationBean();
        bean1.setContent("zct");
        bean1.setTitle("用户名");
        InformationBean bean2 = new InformationBean();
        bean2.setTitle("年龄");
        bean2.setContent("23");
        InformationBean bean3 = new InformationBean();
        bean3.setTitle("性别");
        bean3.setContent("男");
        InformationBean bean4 = new InformationBean();
        bean4.setTitle("电影标签");
        bean4.setContent("悬疑");
        InformationBean bean5 = new InformationBean();
        bean5.setTitle("音乐标签");
        bean5.setContent("放松、思念");


        mList.add(bean);
        mList.add(bean1);
        mList.add(bean2);
        mList.add(bean3);
        mList.add(bean4);
        mList.add(bean5);

    }

    private void setRecycleView() {
        //初始化适配器
        mAdapter = new InformationAdapter(mContext, mList);
        //适配器的点击事件
        mAdapter.setOnItemClickListenr(this);
        //布局的设置
        irvInformation.setLayoutManager(new LinearLayoutManager(mContext));
        //布局每一项的动画效果
        irvInformation.setItemAnimator(new DefaultItemAnimator());
        //分割线 ItemDecoration是可以叠加的
        irvInformation.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        //布局设置适配器
        irvInformation.setIAdapter(mAdapter);
    }

    /**
     * 把数据上传到服务器进行保存
     */
    private void saveInforamtion() {
        // TODO: 2017/10/13 待增加
    }

    @Override
    public void initPresenter() {

    }

    /**
     *
     * 订阅发送来的数据
     * @param info
     */
    @Subscribe
    public void onFavorListEvent(FavorListBean info) {
        //判断数据类型
        if (info.isMovie()) {
            //电影标签 字符串拼接用、分开
            List<String> list = info.getList();
            InformationBean bean = mList.get(4);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    buffer.append(list.get(i));
                } else {
                    buffer.append(list.get(i) + "、");
                }
            }
            //设置判断设置电影标签 true设置 false无设置
            bean.setSet(true);
            //设置电影标签
            bean.setContent(buffer.toString());
            //适配器通知数据改变
            mAdapter.notifyItemChanged(4);
            //设置文本信息变化
            isTextChange = true;
        } else {
            //音乐标签 字符串拼接用、分开
            List<String> list = info.getList();
            InformationBean bean = mList.get(5);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    buffer.append(list.get(i));
                } else {
                    buffer.append(list.get(i) + "、");
                }
            }
            //设置判断设置音乐标签 true设置 false无设置
            bean.setSet(true);
            //设置音乐标签
            bean.setContent(buffer.toString());
            //适配器通知数据改变
            mAdapter.notifyItemChanged(5);
            //设置文本信息变化
            isTextChange = true;
        }

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_my_information;
    }


    /**
     * RecycleView每一项监听事件
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        //个人头像设置
        if (position == 0) {
            //弹出框提示选择图片方式1、打开摄像头拍照 2、从本地的相册中获取图片
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("选择图片");
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_photo_select, (ViewGroup) findViewById(R.id.ll_root), false);
            TextView mTv_take_photo = (TextView) view.findViewById(R.id.tv_take_photo);
            TextView mTv_select_photo = (TextView) view.findViewById(R.id.tv_select_photo);
            builder.setView(view, 100, 30, 100, 30);
            final AlertDialog dialog = builder.create();
            //拍照
            mTv_take_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //首先获取权限，android6.0后需要获取权限才能拍照
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Android M 处理Runtime Permission
                        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {//检查是否有写入SD卡的授权
                            Log.i(TAG, "granted permission!");
                            turnOnCamera();
                        } else {
                            Log.i(TAG, "denied permission!");
                            if (ActivityCompat.shouldShowRequestPermissionRationale(MyInformationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                Log.i(TAG, "should show request permission rationale!");
                            }
                            requestPermission();
                        }
                    } else {
                        turnOnCamera();
                    }
                    if (dialog != null) {
                        dialog.dismiss();
                    }

                }
            });
            //从本地相册中获取图片
            mTv_select_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //首先获取权限
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Android M 处理Runtime Permission
                        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {//检查是否有写入SD卡的授权
                            Log.i(TAG, "granted permission!");
                            turnOnAlbum();
                        } else {
                            Log.i(TAG, "denied permission!");
                            if (ActivityCompat.shouldShowRequestPermissionRationale(MyInformationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                Log.i(TAG, "should show request permission rationale!");
                            }
                            requestPermission();
                        }
                    } else {
                        turnOnAlbum();
                    }
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });
            dialog.show();
        }
        //用户名
        if (position == 1) {
            //弹出框设置
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //弹出框设置标题
            builder.setTitle("修改用户名");
            //弹出框自定义布局
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_edit, (ViewGroup) findViewById(R.id.ll_root), false);
            final EditText editText = (EditText) view.findViewById(R.id.ed_content);
            //设置用户名
            editText.setText(mList.get(1).getContent());
            //显示光标
            editText.setSelection(editText.getText().length());
            builder.setView(view, 100, 30, 100, 30);
            final AlertDialog dialog = builder.create();
            Button bt_positive = (Button) view.findViewById(R.id.bt_positive);
            Button bt_negative = (Button) view.findViewById(R.id.bt_negative);
            //弹出框点击事件的设置
            bt_positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InformationBean bean = mList.get(1);
                    //判断当前用户名是否为空或者是未设置
                    if (!editText.getText().toString().isEmpty() && !editText.getText().toString().equals("未设置")) {
                        bean.setSet(true);
                        //设置用户名
                        bean.setContent(editText.getText().toString());
                        //通知适配器更新数据
                        mAdapter.notifyItemChanged(1);
                        isTextChange = true;
                    }
                    dialog.dismiss();
                }
            });
            bt_negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        //年龄
        if (position == 2) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("修改年龄");
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_edit, (ViewGroup) findViewById(R.id.ll_root), false);
            final EditText editText = (EditText) view.findViewById(R.id.ed_content);
            editText.setText(mList.get(2).getContent());
            editText.setSelection(editText.getText().length());
            builder.setView(view, 100, 30, 100, 30);
            final AlertDialog dialog = builder.create();
            Button bt_positive = (Button) view.findViewById(R.id.bt_positive);
            Button bt_negative = (Button) view.findViewById(R.id.bt_negative);
            bt_positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InformationBean bean = mList.get(2);
                    if (!editText.getText().toString().isEmpty() && !editText.getText().toString().equals("未设置")) {
                        bean.setSet(true);
                        bean.setContent(editText.getText().toString());
                        mAdapter.notifyItemChanged(2);
                        isTextChange = true;
                    }
                    dialog.dismiss();
                }
            });
            bt_negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        //性别
        if (position == 3) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("修改性别");
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_gender_select, (ViewGroup) findViewById(R.id.rg_root), false);
            //用单选择控件
            final RadioButton rb_male = (RadioButton) view.findViewById(R.id.rb_male);
            final RadioButton rb_female = (RadioButton) view.findViewById(R.id.rb_female);
            if (mList.get(3).getContent().equals("男")) {
                rb_male.setChecked(true);
            } else if (mList.get(3).getContent().equals("女")) {
                rb_female.setChecked(true);
            }
            builder.setView(view, 100, 30, 100, 30);
            final AlertDialog dialog = builder.create();
            Button bt_positive = (Button) view.findViewById(R.id.bt_positive);
            Button bt_negative = (Button) view.findViewById(R.id.bt_negative);
            bt_positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InformationBean bean = mList.get(3);
                    if (rb_female.isChecked()) {
                        bean.setContent("女");
                        bean.setSet(true);
                    }
                    if (rb_male.isChecked()) {
                        bean.setContent("男");
                        bean.setSet(true);
                    }
                    mAdapter.notifyItemChanged(3);
                    isTextChange = true;
                    dialog.dismiss();
                }
            });
            bt_negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        //设置电影标签
        if (position == 4) {
            if (mList.get(4).isSet()) {
                String content = mList.get(4).getContent();
                String[] movie = content.split("、");
                favorMovie.clear();
                for (int i = 0; i < movie.length; i++) {
                    favorMovie.add(movie[i]);
                }
            }
            //发送数据给FavorMovieActivity
            Intent intent = new Intent(MyInformationActivity.this, FavorMovieActivity.class);
            intent.putStringArrayListExtra("select", (ArrayList<String>) favorMovie);
            startActivity(intent);
        }
        //设置音乐标签
        if (position == 5) {
            if (mList.get(5).isSet()) {
                String content = mList.get(5).getContent();
                String[] music = content.split("、");
                favorMusic.clear();
                for (int i = 0; i < music.length; i++) {
                    favorMusic.add(music[i]);
                }
            }
            Intent intent = new Intent(MyInformationActivity.this, FavorMusicActivity.class);
            intent.putStringArrayListExtra("select", (ArrayList<String>) favorMusic);
            startActivity(intent);
        }
    }

    /**
     * 开启相册
     */
    private void turnOnAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE);

    }

    /**
     * 开启相机
     */
    private void turnOnCamera() {
        if (mCapturePhotoHelper == null) {
            mCapturePhotoHelper = new CapturePhotoHelper(this, FolderManager.getPhotoFolder());
        }
        mCapturePhotoHelper.capture();
    }

    /**
     * 申请写入sd卡的权限
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RUNTIME_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //应用恢复时 获取拍照保存的文件目录
        if (mCapturePhotoHelper != null) {
            mRestorePhotoFile = (File) savedInstanceState.getSerializable(EXTRA_RESTORE_PHOTO);
            mCapturePhotoHelper.setPhoto(mRestorePhotoFile);
        }
    }

    //拍照完成后 获取目标文件 跳转到裁剪页面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CapturePhotoHelper.CAPTURE_PHOTO_REQUEST_CODE) {
            //获取拍照后图片路径
            File photoFile = mCapturePhotoHelper.getPhoto();

            if (photoFile != null) {
                if (resultCode == RESULT_OK) {
                    Uri uri = Uri.fromFile(photoFile);
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uri, "image/*");
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 300);
                    intent.putExtra("outputY", 300);
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    intent.putExtra("return-data", false);
                    intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                    intent.putExtra("noFaceDetection", true); // no face detection
                    intent = Intent.createChooser(intent, "裁剪图片");
                    startActivityForResult(intent, REQUEST_PICKER_AND_CROP);
                } else {
                    if (photoFile.exists()) {
                        photoFile.delete();
                    }
                }
            }

        } else if (requestCode == REQUEST_PICKER_AND_CROP) {
            File photoFile = mCapturePhotoHelper.getPhoto();
            //存放到相册
            BitmapUtils.displayToGallery(this, photoFile);
            //更新UI 显示图像
            InformationBean informationBean = mList.get(0);
            informationBean.setContent(photoFile.getAbsoluteFile().toString());
            informationBean.setSet(true);
            mAdapter.notifyItemChanged(0);
            isPhotoChange = true;

        } else if (requestCode == REQUEST_PICK_IMAGE) {
            //获取选择图片后图片路径

            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(uri, "image/*");
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 200);
                intent.putExtra("scale", true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                intent.putExtra("return-data", false);
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent.putExtra("noFaceDetection", true); // no face detection
                intent = Intent.createChooser(intent, "裁剪图片");
                startActivityForResult(intent, REQUEST_PICKER_AND_CROP_2);
            }
        } else if (requestCode == REQUEST_PICKER_AND_CROP_2) {

            //更新UI 显示图像
            InformationBean informationBean = mList.get(0);
            System.out.println("adapter中图片url:" + tempFile.getAbsolutePath().toString());
            informationBean.setContent(tempFile.getAbsoluteFile().toString());
            informationBean.setSet(true);
            mAdapter.notifyItemChanged(0);
            isPhotoChange = true;

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //应用闪退时，保留拍照保存的文件目录
        if (mCapturePhotoHelper != null) {
            mRestorePhotoFile = mCapturePhotoHelper.getPhoto();
            if (mRestorePhotoFile != null) {
                outState.putSerializable(EXTRA_RESTORE_PHOTO, mRestorePhotoFile);
            }
        }
    }

    /**
     * 权限返回结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RUNTIME_PERMISSION_REQUEST_CODE) {
            for (int index = 0; index < permissions.length; index++) {
                String permission = permissions[index];
                if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
                    if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                        turnOnCamera();

                    } else {
                        showMissingPermissionDialog();

                    }
                }
            }
        }
    }

    /**
     * 显示打开权限提示的对话框
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("帮助");
        builder.setMessage("当前权限被禁用，建议到设置界面开启权限!");

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyInformationActivity.this, "启动相机失败", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                turnOnSettings();
            }
        });

        builder.show();
    }

    /**
     * 启动系统权限设置界面
     */
    private void turnOnSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 回退键保存数据
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveInforamtion();
    }
}
