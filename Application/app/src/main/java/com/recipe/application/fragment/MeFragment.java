package com.recipe.application.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.recipe.application.R;
import com.recipe.application.activity.DynamicActivity;
import com.recipe.application.dao.Post;
import com.recipe.application.dao.User;
import com.recipe.application.utils.BitmapUtils;
import com.recipe.application.utils.CameraUtils;
import com.recipe.application.utils.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




public class MeFragment extends Fragment {

    //权限请求
    private RxPermissions rxPermissions;
    //是否拥有权限
    private boolean hasPermissions = false;
    //底部弹窗
    private BottomSheetDialog bottomSheetDialog;
    //弹窗视图
    private View bottomView;
    //存储拍完照后的图片
    private File outputImagePath;
    //启动相机标识
    public static final int TAKE_PHOTO = 1;
    //启动相册标识
    public static final int SELECT_PHOTO = 2;

    //图片控件
    private ShapeableImageView ivHead;
    //拍照和相册获取图片的Bitmap
    private Bitmap orc_bitmap;

    private View rootView;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private TabHost tabHost;

    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存


    public MeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_me, container, false);

        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        ivHead = rootView.findViewById(R.id.iv_head);

        //动态加载碎片。进入主界面界面后显示的界面，默认进入复习页面
        FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.tabcontent, new RecipeFragment());
        fragmentTransaction.commit();

        initView();

        TextView takeName=rootView.findViewById(R.id.take_name);
        if(!preferences.getString("name","").equals("")){
            String name=preferences.getString("name","");
            takeName.setText(name);
        }
        takeName.setOnClickListener(v -> {
            final EditText input= new EditText(getContext());
            input.setFocusable(true);

            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("请在下方输入新的昵称").setView(input).setNegativeButton(
                    "否", null);
            alert.setPositiveButton("是", (dialog5, which) -> {
                String inputName = input.getText().toString();
                editor=preferences.edit();
                editor.putString("name",inputName);
                editor.apply();
                String name=preferences.getString("name","");
                takeName.setText(name);
                final User user=new User();
                user.setName(inputName);
//                user.update(new UpdateListener() {
//                    @Override
//                    public void done(BmobException e) {
//
//                    }
//                });
            });
            alert.show();
        });

        TextView takeLabel=rootView.findViewById(R.id.take_label);
        if(!preferences.getString("label","").equals("")){
            String label=preferences.getString("label","");
            takeLabel.setText(label);
        }
        takeLabel.setOnClickListener(v -> {
            final EditText input= new EditText(getContext());
            input.setFocusable(true);

            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("请在下方输入新的个性标签").setView(input).setNegativeButton(
                    "否", null);
            alert.setPositiveButton("是", (dialog5, which) -> {
                String inputName = input.getText().toString();
                editor=preferences.edit();
                editor.putString("label",inputName);
                editor.apply();
                String label=preferences.getString("label","");
                takeLabel.setText(label);
                final User user=new User();
                user.setLabel(label);
//                user.update(new UpdateListener() {
//                    @Override
//                    public void done(BmobException e) {
//
//                    }
//                });
            });
            alert.show();
        });


        //取出缓存
        String imageUrl = SPUtils.getString("imageUrl",null,getContext());
        if(imageUrl != null){
            Glide.with(this).load(imageUrl).apply(requestOptions).into(ivHead);
        }

        return rootView;
    }

    private void checkVersion() {
        //Android6.0及以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果你是在Fragment中，则把this换成getActivity()
            RxPermissions rxPermissions = new RxPermissions(getActivity());
            //权限请求
            rxPermissions
                    .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {//申请成功
                            showMsg("已获取权限");
                            hasPermissions = true;
                        } else {//申请失败
                            showMsg("权限未开启");
                            hasPermissions = false;
                        }
                    });
        } else {
            //Android6.0以下
            showMsg("无需请求动态权限");
        }
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String filename = timeStampFormat.format(new Date());
        outputImagePath = new File(getContext().getExternalCacheDir(),
                filename + ".jpg");
        Intent takePhotoIntent = CameraUtils.getTakePhotoIntent(getContext(), outputImagePath);
        // 开启一个带有返回值的Activity，请求码为TAKE_PHOTO
        startActivityForResult(takePhotoIntent, TAKE_PHOTO);
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        startActivityForResult(CameraUtils.getSelectPhotoIntent(), SELECT_PHOTO);
    }

    /**
     * 更换头像
     */
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ShapeableImageView imageView=rootView.findViewById(R.id.iv_head);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog = new BottomSheetDialog(getContext());
                bottomView = getLayoutInflater().inflate(R.layout.dialog_bottom, null);
                bottomSheetDialog.setContentView(bottomView);
                bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT);
                TextView tvTakePictures = bottomView.findViewById(R.id.tv_take_pictures);
                TextView tvOpenAlbum = bottomView.findViewById(R.id.tv_open_album);
                TextView tvCancel = bottomView.findViewById(R.id.tv_cancel);

                //拍照
                tvTakePictures.setOnClickListener(v -> {
                    takePhoto();
                    showMsg("拍照");
                    bottomSheetDialog.cancel();
                });
                //打开相册
                tvOpenAlbum.setOnClickListener(v -> {
                    openAlbum();
                    showMsg("打开相册");
                    bottomSheetDialog.cancel();
                });
                //取消
                tvCancel.setOnClickListener(v -> {
                    bottomSheetDialog.cancel();
                });
                //底部弹窗显示
                bottomSheetDialog.show();
            }
        });
    }






    /**
     * 返回到Activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //拍照后返回
            case TAKE_PHOTO:
                if (resultCode == -1) {
                    //显示图片
                    displayImage(outputImagePath.getAbsolutePath());
                }
                break;
            //打开相册后返回
            case SELECT_PHOTO:
                if (resultCode == -1) {
                    String imagePath = null;
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePath = CameraUtils.getImageOnKitKatPath(data, getContext());
                    } else {
                        imagePath = CameraUtils.getImageBeforeKitKatPath(data, getContext());
                    }

                    //放入缓存
                    SPUtils.putString("imageUrl",imagePath,getContext());

                    //显示图片
                    displayImage(imagePath);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 通过图片路径显示图片
     */
    private void displayImage(String imagePath) {

        if (!TextUtils.isEmpty(imagePath)) {

            //放入缓存
            SPUtils.putString("imageUrl",imagePath,getContext());

            final User user=new User();
            user.setImage(imagePath);

            Toast.makeText(getContext(),"存入数据库成功",Toast.LENGTH_SHORT).show();



            //显示图片
            Glide.with(this).load(imagePath).apply(requestOptions).into(ivHead);

            //压缩图片
            orc_bitmap = CameraUtils.compression(BitmapFactory.decodeFile(imagePath));
            //转Base64
            //Base64
            String base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);

        } else {
            showMsg("图片获取失败");
        }
    }


    private void initView() {
        tabHost=rootView.findViewById(R.id.tabhost);
        tabHost.setup();

        LayoutInflater i=LayoutInflater.from(getContext());
        i.inflate(R.layout.fragment_recipe,tabHost.getTabContentView());
        i.inflate(R.layout.fragment_work,tabHost.getTabContentView());
        i.inflate(R.layout.fragment_collection,tabHost.getTabContentView());
        i.inflate(R.layout.fragment_fan,tabHost.getTabContentView());


        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab1")
                .setIndicator("菜谱", ResourcesCompat.getDrawable(getResources(), R.drawable.work, null))
                .setContent(R.id.fragment_recipe);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab2")
                .setIndicator("关注", ResourcesCompat.getDrawable(getResources(), R.drawable.work,null))
                .setContent(R.id.fragment_work);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab3")
                .setIndicator("收藏",ResourcesCompat.getDrawable(getResources(),  R.drawable.work, null))
                .setContent(R.id.fragment_collection);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab4")
                .setIndicator("粉丝",ResourcesCompat.getDrawable(getResources(),  R.drawable.work, null))
                .setContent(R.id.fragment_fan);
        tabHost.addTab(tabSpec);

        tabHost.getTabWidget().setShowDividers(0);

        //记录tabHost的点击事件
        tabHost.setOnTabChangedListener(tabId -> {
            if(tabId.equals("tab1")){
                //点击tab1动态加载复习页面
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.tabcontent, new RecipeFragment());
                fragmentTransaction.commit();
            }
            if(tabId.equals("tab2")){
                //点击tab2进入测试页面
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.tabcontent, new WorkFragment());
                fragmentTransaction.commit();
            }
            if(tabId.equals("tab3")){
                //点击tab3进入设置页面
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.tabcontent, new CollectionFragment());
                fragmentTransaction.commit();

            }
            if(tabId.equals("tab4")){
                //点击tab3进入设置页面
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.tabcontent, new FanFragment());
                fragmentTransaction.commit();

            }
        });

    }
    private void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
