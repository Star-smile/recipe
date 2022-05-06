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

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

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
                BmobQuery<User> userBmobQuery = new BmobQuery<>();
                userBmobQuery.addWhereEqualTo("username", account);
                userBmobQuery.findObjects(new FindListener<User>() {
                    @Override
                    public void done(List<User> list, BmobException e) {
                        if (e == null) {
                            for (User user : list) {
                                String url = user.getImage();
                                String u_name = user.getName();
                                BmobFile picture = user.getPicture();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");// HH:mm:ss
                                Date date = new Date(System.currentTimeMillis());
                                String createDate = simpleDateFormat.format(date);
                                String content = send.getText().toString();
                                Comment comment = new Comment();
                                comment.setImage(url);
                                comment.setContent(content);
                                comment.setTime(createDate);
                                comment.setName(u_name);
                                comment.setPicture(picture);
                                Post post = new Post();
                                post.setObjectId(objectId);
                                comment.setId(post);
                                comment.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e == null) {
                                            send.setText("");
                                            Toast.makeText(ShowActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                showAppraisal(objectId);

                            }
                        }
                    }
                });
            }
        });

        look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmp = !tmp;
                if (tmp) {
                    look.setText("已关注");
                    final User user = BmobUser.getCurrentUser(User.class);
                    String who = user.getName();
                    String name = author.getText().toString();
                    Focus focus = new Focus();
                    focus.setName(name);
                    focus.setWho(who);
                    focus.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {

                        }
                    });
                } else {
                    look.setText("关注");
                    final User user = BmobUser.getCurrentUser(User.class);
                    String who = user.getName();
                    BmobQuery<Focus> focusBmobQuery = new BmobQuery<>();
                    focusBmobQuery.addWhereEqualTo("who", who);
                    focusBmobQuery.findObjects(new FindListener<Focus>() {
                        @Override
                        public void done(List<Focus> list, BmobException e) {
                            if (e == null) {
                                String name = author.getText().toString();
                                for (Focus focus : list) {
                                    if (name.equals(focus.getName())) {
                                        String objectId = focus.getObjectId();
                                        Focus focus1 = new Focus();
                                        focus1.delete(objectId, new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {

                                            }
                                        });
                                    }
                                }
                            }

                        }
                    });
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
                    final User user = BmobUser.getCurrentUser(User.class);
                    Post post = new Post();
                    post.setObjectId(objectId);
                    Like like = new Like();
                    like.setPostId(post);
                    like.setName(user.getName());
                    like.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {

                        }
                    });

                } else {
                    myLike.setText("收藏");
                    like.setImageResource(R.drawable.unselection_collection);
                    BmobQuery<Like> likeBmobQuery = new BmobQuery<>();
                    likeBmobQuery.findObjects(new FindListener<Like>() {
                        @Override
                        public void done(List<Like> list, BmobException e) {
                            if (e == null) {
                                for (Like like : list) {
                                    String object = like.getPostId().getObjectId();
                                    if (object.equals(objectId)) {
                                        String m_object = like.getObjectId();
                                        Like like1 = new Like();
                                        like1.delete(m_object, new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {

                                            }
                                        });
                                    }
                                }
                            }
                        }
                    });
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
        final User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<Like> likeBmobQuery = new BmobQuery<>();
        likeBmobQuery.addWhereEqualTo("name", user.getName());
        likeBmobQuery.findObjects(new FindListener<Like>() {
            @Override
            public void done(List<Like> list, BmobException e) {
                if (e == null) {
                    for (Like my_like : list) {
                        String my = my_like.getPostId().getObjectId();
                        if (objectId.equals(my)) {
                            myLike.setText("已收藏");
                            like.setImageResource(R.drawable.selcetion_collection);
                        }
                    }
                }
            }
        });
        BmobQuery<Focus> focusBmobQuery = new BmobQuery<>();
        focusBmobQuery.addWhereEqualTo("who", user.getName());
        focusBmobQuery.findObjects(new FindListener<Focus>() {
            @Override
            public void done(List<Focus> list, BmobException e) {
                if (e == null) {
                    for (Focus focus : list) {
                        String name = author.getText().toString();
                        String myName = focus.getName();
                        if (myName.equals(name)) {
                            look.setText("已关注");
                            tmp = true;
                        }
                    }
                }
            }
        });
    }

    public void show(String objectId) {

        BmobQuery<Post> postBmobQuery = new BmobQuery<>();
        postBmobQuery.addWhereEqualTo("objectId", objectId);
        postBmobQuery.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if (e == null) {
                    for (Post post : list) {
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
                }
            }
        });

    }

    private void showcase(String objectId) {
        BmobQuery<Materials> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("id", objectId);
        bmobQuery.findObjects(new FindListener<Materials>() {
            @Override
            public void done(List<Materials> list, BmobException e) {
                if (e == null) {
                    materialsAdapter = new MaterialsAdapter(ShowActivity.this, list);
                    listIngredient.setAdapter(materialsAdapter);
                }
            }
        });
    }

    private void showUsage(String objectId) {
        BmobQuery<Method> methodBmobQuery = new BmobQuery<>();
        methodBmobQuery.addWhereEqualTo("id", objectId);
        methodBmobQuery.order("sequence");
        methodBmobQuery.findObjects(new FindListener<Method>() {
            @Override
            public void done(List<Method> list, BmobException e) {
                if (e == null) {
                    usageAdapter = new UsageAdapter(ShowActivity.this, list);
                    listUsage.setAdapter(usageAdapter);
                }
            }
        });
    }

    private void showAppraisal(String objectId) {
        BmobQuery<Comment> commentBmobQuery = new BmobQuery<>();
        commentBmobQuery.addWhereEqualTo("id", objectId);
        commentBmobQuery.order("-updatedAt");
        commentBmobQuery.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e == null) {
                    appraisalAdapter = new AppraisalAdapter(ShowActivity.this, list);
                    listAppraisal.setAdapter(appraisalAdapter);
                }
            }
        });
    }
}