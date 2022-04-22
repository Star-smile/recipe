package com.recipe.application.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.recipe.application.R;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.recipe.application.dao.User;
import com.recipe.application.utils.SPUtils;

import java.io.Serializable;


public class MeFragment extends Fragment {

    private View rootView;
    private SharedPreferences preferences;
    //图片控件
    private ShapeableImageView ivHead;
    private SharedPreferences.Editor editor;


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

    private void initView() {
    }

}
