package com.recipe.application.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.recipe.application.R;
import com.recipe.application.adapter.AppraisalAdapter;
import com.recipe.application.adapter.MaterialsAdapter;
import com.recipe.application.adapter.UsageAdapter;
import com.recipe.application.dao.Comment;
import com.recipe.application.dao.Focus;
import com.recipe.application.dao.Like;
import com.recipe.application.dao.Materials;
import com.recipe.application.dao.Method;
import com.recipe.application.dao.Post;
import com.recipe.application.dao.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class ShowActivity extends AppCompatActivity {

    private ImageView cover;
    private TextView name;
    private TextView author;
    private TextView introduction;
    private ListView listIngredient;
    private ListView listUsage;
    private TextView tip;
    private String objectId;
    private ListView listAppraisal;
    private Button summit;
    private EditText send;
    private Button look;

    private ImageView like;
    private TextView myLike;

    private MaterialsAdapter materialsAdapter;
    private UsageAdapter usageAdapter;
    private AppraisalAdapter appraisalAdapter;

    private SharedPreferences preferences;

    private boolean tmp = false;
    private boolean isClick = false;


    //设置图片圆角角度
    RoundedCorners roundedCorners = new RoundedCorners(6);
    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .skipMemoryCache(false);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

    }

}