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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        init();
        Intent intent = getIntent();
        objectId = intent.getStringExtra("objectId");
        show(objectId);
        showLike(objectId);
        showcase(objectId);
        showUsage(objectId);
        showAppraisal(objectId);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String account = preferences.getString("account", "");
        Log.d("ShowActivity", account);
        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                String url = user.getImage();
                String u_name = user.getName();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");// HH:mm:ss
                Date date = new Date(System.currentTimeMillis());
                String createDate = simpleDateFormat.format(date);
                String content = send.getText().toString();
                Comment comment = new Comment();
                comment.setImage(url);
                comment.setContent(content);
                comment.setTime(createDate);
                comment.setName(u_name);
                Post post = new Post();
                comment.setId(post);
                send.setText("");
                Toast.makeText(ShowActivity.this, "评论成功", Toast.LENGTH_SHORT).show();


                showAppraisal(objectId);


            }

        });


        look.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        tmp = !tmp;
        if (tmp) {
            look.setText("已关注");
            final User user = new User();
            String who = user.getName();
            String name = author.getText().toString();
            Focus focus = new Focus();
            focus.setName(name);
            focus.setWho(who);

        } else {
            look.setText("关注");
            final User user = new User();
            String who = user.getName();
            Focus focus = new Focus();

            String name = author.getText().toString();
                            if (name.equals(focus.getName())) {
                                Focus focus1 = new Focus();

                            }




            }
        }

    });


        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = !isClick;
                if (isClick) {
                    myLike.setText("已收藏");
                    like.setImageResource(R.drawable.selcetion_collection);
                    final User user = new User();
                    Post post = new Post();
                    Like like = new Like();
                    like.setPostId(post);
                    like.setName(user.getName());


                } else {
                    myLike.setText("收藏");
                    like.setImageResource(R.drawable.unselection_collection);

                }
            }
        });
    }


    public void init() {
        cover = findViewById(R.id.cover);
        name = findViewById(R.id.name);
        author = findViewById(R.id.author);
        introduction = findViewById(R.id.introduction);
        listIngredient = findViewById(R.id.list_ingredient);
        listUsage = findViewById(R.id.list_usage);
        tip = findViewById(R.id.tip);
        listAppraisal = findViewById(R.id.list_appraisal);
        send = findViewById(R.id.send);
        summit = findViewById(R.id.summit);
        look = findViewById(R.id.look);
        like = findViewById(R.id.like);
        myLike = findViewById(R.id.my_like);
    }

    public void showLike(String objectId) {
        final User user = new User();
        Like my_like = new Like();

        String my = my_like.getPostId().toString();
        if (objectId.equals(my)) {
            myLike.setText("已收藏");
            like.setImageResource(R.drawable.selcetion_collection);
        }

        Focus focus = new Focus();

        String names = author.getText().toString();
        String myName = focus.getName().toString();
            if(names.equals((myName))){
                look.setText("已关注");
                tmp = true;
            }


    }

    public void show(String objectId) {


          Post post=new Post();
                        //MyBitmapUtils myBitmapUtils=new MyBitmapUtils();
                        //myBitmapUtils.disPlay(cover,post.getCover());
                        Glide.with(ShowActivity.this).load(post.getCover()).apply(requestOptions).into(cover);
                        name.setText(post.getName());
                        author.setText(post.getPeople());
                        introduction.setText(post.getIntroduction());
                        if (post.getTip() != null) {
                            tip.setText(post.getTip());
                        } else {
                            tip.setText("let's go");
                        }
                    }




    private void showcase(String objectId) {
     List list=new ArrayList<Materials>();
        materialsAdapter = new MaterialsAdapter(ShowActivity.this,list);
        listIngredient.setAdapter(materialsAdapter);


    }
    private void showUsage(String objectId){
        List list=new ArrayList<UsageAdapter>();

        usageAdapter=new UsageAdapter(ShowActivity.this,list);
                    listUsage.setAdapter(usageAdapter);

    }


    private void showAppraisal(String objectId) {
        List list=new ArrayList<AppraisalAdapter>();

        appraisalAdapter = new AppraisalAdapter(ShowActivity.this, list);
                    listAppraisal.setAdapter(appraisalAdapter);
                }
}