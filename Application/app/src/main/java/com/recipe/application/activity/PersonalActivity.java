package com.recipe.application.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.recipe.application.R;
import com.recipe.application.dao.Focus;
import com.recipe.application.dao.User;
import com.recipe.application.fragment.CollectionFragment;
import com.recipe.application.fragment.FanFragment;
import com.recipe.application.fragment.RecipeFragment;
import com.recipe.application.fragment.WorkFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import com.recipe.application.R;

public class PersonalActivity extends AppCompatActivity {

    private boolean tmp=false;

    private TabHost tabHost;

    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ShapeableImageView ivHead=findViewById(R.id.iv_head);
        TextView takeName=findViewById(R.id.take_name);
        TextView takeLabel=findViewById(R.id.take_label);
        Button focus=findViewById(R.id.focus);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.tabcontent, new RecipeFragment());
        fragmentTransaction.commit();

        initView();

        //initView();
        Intent intent =getIntent();
        String objectId=intent.getStringExtra("objectId");
        final User user=new User();
        takeName.setText(user.getName());
        takeLabel.setText(user.getLabel());
        //显示图片
        Glide.with(PersonalActivity.this).load(user.getImage()).apply(requestOptions).into(ivHead);
        final User my_user =new User();
        Focus my_focus=new Focus();
        String myName=my_focus.getName();
        if(myName.equals(user.getName())){
            my_focus.setText("已关注");
            tmp=true;
        }






        focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmp=!tmp;
                if(tmp){
                    focus.setText("已关注");
                    final User user = new User();
                    String who=user.getName();
                    String name=takeName.getText().toString();
                    Focus focus=new Focus();
                    focus.setName(name);
                    focus.setWho(who);
                }else{
                    focus.setText("关注");
                    final User user = new User();
                    Focus focus=new Focus();
                    focus.setText("关注");
                    String who=user.getName();
                    String name=takeName.getText().toString();
                        if(name.equals(focus.getName())){
                            Focus focus1=new Focus();

                        }


                }
            }
        });
    }

    private void initView(){
        tabHost=findViewById(R.id.tabhost);
        tabHost.setup();

        LayoutInflater i=LayoutInflater.from(this);
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
                FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.tabcontent, new RecipeFragment());
                fragmentTransaction.commit();
            }
            if(tabId.equals("tab2")){
                //点击tab2进入测试页面
                FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.tabcontent, new WorkFragment());
                fragmentTransaction.commit();
            }
            if(tabId.equals("tab3")){
                //点击tab3进入设置页面
                FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.tabcontent, new CollectionFragment());
                fragmentTransaction.commit();

            }
            if(tabId.equals("tab4")){
                //点击tab3进入设置页面
                FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.tabcontent, new FanFragment());
                fragmentTransaction.commit();

            }
        });
    }
}