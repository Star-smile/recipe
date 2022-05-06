package com.recipe.application.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.recipe.application.MainActivity;
import com.recipe.application.R;
import com.recipe.application.dao.Brief_dish;
import com.recipe.application.dao.Materials;
import com.recipe.application.dao.Method;
import com.recipe.application.dao.Post;
import com.recipe.application.dao.User;
import com.recipe.application.utils.BitmapUtils;
import com.recipe.application.utils.CameraUtils;
import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class DynamicActivity extends AppCompatActivity implements View.OnClickListener{

    //权限请求
    private RxPermissions rxPermissions;
    //是否拥有权限
    private boolean hasPermissions = false;
    //启动相册标识
    public static final int SELECT_PHOTO = 2;
    private EditText name;
    private ImageButton process;
    //Base64
    private String base64Pic;
    //拍照和相册获取图片的Bitmap
    private Bitmap orc_bitmap;

    private EditText recipe;
    private EditText consumption;
    private ImageView del;
    private ImageView move;
    private TextView add;
    private TextView change;
    private LinearLayout layout;
    private boolean flag=false;

    private LinearLayout linearLayout;
    private LinkedList<ImageView> listDel;
    private LinkedList<EditText> listIngredient;
    private LinkedList<EditText> listConsume;
    private LinkedList<ImageView> listMove;

    private LinearLayout processLayout;
    private TextView process1;
    private ImageButton image1;
    private TextView explain1;
    private TextView process2;
    private ImageButton image2;
    private TextView explain2;
    private TextView addProcess;
    private TextView changeProcess;
    private ImageView del1;
    private ImageView move1;
    private ImageView del2;
    private ImageView move2;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private boolean tmp=false;
    private int i=0;
    private int n=2;

    private Button salt;
    private Button sauce;
    private Button pepper;
    private Button hot_pepper;
    private Button green_pepper;
    private Button egg;
    private Button vinegar;
    private Button spice;
    private Button onion;
    private Button fragrance;
    private Button garlic;
    private Button ginger;
    private Button chicken;
    private Button sugar;
    private Button wine;

    private RadioGroup group;
    private RadioGroup radioGroup;

    private ImageButton tmp_image;

    private Button publish;
    private EditText brief;
    private EditText point;
    private String objectId;
    private String p_objectId;
    private String m_objectId;
    private SharedPreferences preferences;
    private boolean isPublish=false;
    private String use_time;
    private String degree;

    private List<Button> buttons=new ArrayList<>();

    private LinkedList<TextView> listProcess;
    private LinkedList<ImageButton> listImage;
    private LinkedList<TextView> listExplain;
    private LinkedList<ImageView> listDelProcess;
    private LinkedList<ImageView> listMoveProcess;

    private Map<String,String> map=new HashMap<>();
    private Map<ImageView,String> stringMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        Intent intent=getIntent();
        objectId=intent.getStringExtra("objectId");
        init();
        initData();
        listMove=new LinkedList<>();
        listConsume=new LinkedList<>();
        listDel=new LinkedList<>();
        listIngredient=new LinkedList<>();

        listProcess=new LinkedList<>();
        listImage=new LinkedList<>();
        listExplain=new LinkedList<>();
        listDelProcess=new LinkedList<>();
        listMoveProcess=new LinkedList<>();

        process.setOnClickListener(this);
        change.setOnClickListener(this);
        add.setOnClickListener(this);
        del.setOnClickListener(this);

        changeProcess.setOnClickListener(this);
        addProcess.setOnClickListener(this);

        del1.setOnClickListener(this);
        del2.setOnClickListener(this);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);

        publish.setOnClickListener(this);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton =group.findViewById(checkedId);
                use_time = radioButton.getText().toString();

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton =group.findViewById(checkedId);
                degree = radioButton.getText().toString();
            }
        });

        for(Button button:buttons){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name="食材：如鸡蛋";
                    switch (v.getId()){
                        case R.id.salt:
                            name="食盐";
                            break;
                        case R.id.hot_pepper:
                            name="辣椒";
                            break;
                        case R.id.green_pepper:
                            name="青椒";
                            break;
                        case R.id.pepper:
                            name="花椒";
                            break;
                        case R.id.vinegar:
                            name="食醋";
                            break;
                        case R.id.sauce:
                            name="老抽";
                            break;
                        case R.id.fragrance:
                            name="香菜";
                            break;
                        case R.id.wine:
                            name="料酒";
                            break;
                        case R.id.egg:
                            name="鸡蛋";
                            break;
                        case R.id.spice:
                            name="五香粉";
                            break;
                        case R.id.sugar:
                            name="冰糖";
                            break;
                        case R.id.garlic:
                            name="葱";
                            break;
                        case R.id.onion:
                            name="蒜";
                            break;
                        case R.id.chicken:
                            name="鸡精";
                            break;
                        case R.id.ginger:
                            name="生姜";
                            break;
                    }
                    add(name);
                }
            });
        }

    }

    private void init(){
        linearLayout=findViewById(R.id.linear);
        name=findViewById(R.id.name);
        process=findViewById(R.id.process);
        del=findViewById(R.id.del);
        move=findViewById(R.id.move);
        add=findViewById(R.id.add);
        change=findViewById(R.id.change);
        recipe=findViewById(R.id.recipe);
        consumption=findViewById(R.id.consumption);
        layout =findViewById(R.id.linear_Layout);

        processLayout=findViewById(R.id.process_layout);
        process1=findViewById(R.id.process1);
        image1=findViewById(R.id.image1);
        explain1=findViewById(R.id.explain1);
        process2=findViewById(R.id.process2);
        image2=findViewById(R.id.image2);
        explain2=findViewById(R.id.explain2);
        addProcess=findViewById(R.id.add_process);
        changeProcess=findViewById(R.id.change_process);
        del1=findViewById(R.id.del1);
        move1=findViewById(R.id.move1);
        del2=findViewById(R.id.del2);
        move2=findViewById(R.id.move2);
        linear1=findViewById(R.id.linear1);
        linear2=findViewById(R.id.linear2);

        salt=findViewById(R.id.salt);
        sugar=findViewById(R.id.sugar);
        sauce=findViewById(R.id.sauce);
        garlic=findViewById(R.id.garlic);
        onion=findViewById(R.id.onion);
        pepper=findViewById(R.id.pepper);
        hot_pepper=findViewById(R.id.hot_pepper);
        green_pepper=findViewById(R.id.green_pepper);
        egg=findViewById(R.id.egg);
        spice=findViewById(R.id.spice);
        fragrance=findViewById(R.id.fragrance);
        ginger=findViewById(R.id.ginger);
        wine=findViewById(R.id.wine);
        chicken=findViewById(R.id.chicken);
        vinegar=findViewById(R.id.vinegar);

        group=findViewById(R.id.group);
        radioGroup=findViewById(R.id.radioGroup);

        publish=findViewById(R.id.publish);
        brief=findViewById(R.id.story);
        point=findViewById(R.id.tip);
    }
    private void initData(){
        map.put("食盐","克");
        map.put("老抽","勺");
        map.put("葱","根");
        map.put("花椒","粒");
        map.put("料酒","勺");
        map.put("鸡精","勺");
        map.put("蒜","瓣");
        map.put("生姜","克");
        map.put("食醋","勺");
        map.put("青椒","根");
        map.put("鸡蛋","个");
        map.put("五香粉","勺");
        map.put("辣椒","勺");
        map.put("香菜","克");
        map.put("冰糖","克");
        map.put("食材：如鸡蛋","用量：如1个");

        buttons.add(salt);
        buttons.add(sugar);
        buttons.add(sauce);
        buttons.add(vinegar);
        buttons.add(hot_pepper);
        buttons.add(green_pepper);
        buttons.add(spice);
        buttons.add(garlic);
        buttons.add(ginger);
        buttons.add(pepper);
        buttons.add(onion);
        buttons.add(chicken);
        buttons.add(egg);
        buttons.add(fragrance);
        buttons.add(wine);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.process:
                openAlbum(process);
                break;
            case R.id.change:
                flag=!flag;
                if(flag==true) {
                    change.setText("调整完成");
                    add.setVisibility(View.GONE);
                    del.setVisibility(View.VISIBLE);
                    move.setVisibility(View.VISIBLE);
                    for(ImageView imageView:listDel){
                        imageView.setVisibility(View.VISIBLE);
                    }
                    for(ImageView view1:listMove){
                        view1.setVisibility(View.VISIBLE);
                    }
                }else{
                    change.setText("调整用量");
                    add.setVisibility(View.VISIBLE);
                    del.setVisibility(View.GONE);
                    move.setVisibility(View.GONE);
                    for(ImageView imageView:listDel){
                        imageView.setVisibility(View.GONE);
                    }
                    for(ImageView view1:listMove){
                        view1.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.add:
                add("食材：如鸡蛋");
            case R.id.del:
                layout.setVisibility(View.GONE);
                break;
            case R.id.change_process:
                tmp=!tmp;
                if(tmp){
                    changeProcess.setText("调整完成");
                    addProcess.setVisibility(View.GONE);
                    del1.setVisibility(View.VISIBLE);
                    del2.setVisibility(View.VISIBLE);
                    move1.setVisibility(View.VISIBLE);
                    move2.setVisibility(View.VISIBLE);
                    for(ImageView imageView:listDelProcess){
                        imageView.setVisibility(View.VISIBLE);
                    }
                    for(ImageView imageView:listMoveProcess){
                        imageView.setVisibility(View.VISIBLE);
                    }
                }else{
                    changeProcess.setText("调整步骤");
                    addProcess.setVisibility(View.VISIBLE);
                    del1.setVisibility(View.GONE);
                    del2.setVisibility(View.GONE);
                    move1.setVisibility(View.GONE);
                    move2.setVisibility(View.GONE);
                    for(ImageView imageView:listDelProcess){
                        imageView.setVisibility(View.GONE);
                    }
                    for(ImageView imageView:listMoveProcess){
                        imageView.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.add_process:
                LinearLayout linear_process = new LinearLayout(this);
                linear_process.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linear_process.setLayoutParams(layoutParams);

                ImageView del_process=new ImageView(this);
                LinearLayout.LayoutParams delProcessPar = new LinearLayout.LayoutParams
                        (0, 90,0.5f );
                delProcessPar.setMargins(10,5,5,5);
                delProcessPar.gravity= Gravity.CENTER;
                del_process.setLayoutParams(delProcessPar);
                del_process.setImageResource(R.drawable.del);
                del_process.setBackgroundColor(Color.argb(255, 255, 0, 0));
                del_process.setVisibility(View.GONE);
                del_process.setClickable(true);
                del_process.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delProcess(v);
                    }
                });
                linear_process.addView(del_process);
                listDelProcess.add(del_process);

                LinearLayout linearLayout=new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                        0, ViewGroup.LayoutParams.WRAP_CONTENT,3f);
                linearLayout.setLayoutParams(layoutParam);

                TextView processes=new TextView(this);
                LinearLayout.LayoutParams processesPar = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                processesPar.setMargins(5,10,0,10);
                processes.setLayoutParams(processesPar);
                processes.setText("步骤"+(listProcess.size()+3-i));
                listProcess.add(processes);
                linearLayout.addView(processes);

                ImageButton image_process=new ImageButton(this);
                LinearLayout.LayoutParams imagePar = new LinearLayout.LayoutParams
                        (900,600);
                imagePar.setMargins(5,20,0,5);
                imagePar.gravity= Gravity.CENTER;
                image_process.setLayoutParams(imagePar);
                image_process.setScaleType(ImageView.ScaleType.FIT_XY);
                image_process.setClickable(true);
                image_process.setImageResource(R.drawable.process);
                image_process.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openAlbum(image_process);
                    }
                });
                listImage.add(image_process);
                linearLayout.addView(image_process);

                EditText explain_process=new EditText(this);
                LinearLayout.LayoutParams explainPar = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                explainPar.setMargins(5,5,5,5);
                explain_process.setLayoutParams(explainPar);
                explain_process.setHint("请添加步骤说明，至少2步");
                listExplain.add(explain_process);
                linearLayout.addView(explain_process);
                linear_process.addView(linearLayout);

                ImageView move_process=new ImageView(this);
                LinearLayout.LayoutParams move_proPar = new LinearLayout.LayoutParams
                        (0,90,0.5f);
                move_proPar.setMargins(3,3,3,3);
                move_proPar.gravity=Gravity.CENTER;
                move_process.setLayoutParams(move_proPar);
                move_process.setClickable(true);
                move_process.setVisibility(View.GONE);
                move_process.setImageResource(R.drawable.move);
                listMoveProcess.add(move_process);
                linear_process.addView(move_process);
                processLayout.addView(linear_process);
                break;
            case R.id.del1:
                if(i==0){
                    i=1;
                }else{
                    i=2;
                }
                if(n==2){
                    n=1;
                    process2.setText("步骤1");
                    showNum(n);
                }else{
                    n=0;
                    showNum(n);
                }
                linear1.setVisibility(View.GONE);
                break;
            case R.id.del2:
                if(i==0){
                    i=1;
                }else{
                    i=2;
                }
                if(n==2){
                    n=1;
                    showNum(n);
                }else{
                    n=0;
                    showNum(n);
                }
                linear2.setVisibility(View.GONE);
                break;
            case R.id.image1:
                openAlbum(image1);
                break;
            case R.id.image2:
                openAlbum(image2);
                break;
            case R.id.publish:
                isPublish=true;
                preferences= PreferenceManager.getDefaultSharedPreferences(this);
                String account=preferences.getString("account","");




                //发布菜谱接口
                new Thread(){
                    @Override
                    public void run() {
                        try {

                            //post信息
                            String cus_name=account;
                            String recipe_name=name.getText().toString();
                            String ingredient=recipe.getText().toString();
                            String consume=consumption.getText().toString();
                            String tip=point.getText().toString();
                            String story=brief.getText().toString();
                            String cover=stringMap.get(process);
                            Post post=new Post();
                            post.setName(recipe_name);
                            post.setIntroduction(story);
                            post.setTip(tip);
                            post.setPeople(cus_name);
                            post.setDegree(degree);
                            post.setUser_time(use_time);
                            post.setCover(cover);
                            Brief_dish dish=new Brief_dish();

                            post.setType(dish);
                            //materials
                            Materials materials = new Materials();

                            if(layout.getVisibility()==View.VISIBLE) {
                                materials.setIngredient(ingredient);
                                materials.setConsumption(consume);
                                Post post1=new Post();
                                materials.setId(post1);

                            }
                            if(listIngredient.size()!=0){
                                for(int i=0;i<listIngredient.size();i++) {
                                    String use_ingredient = listIngredient.get(i).getText().toString();
                                    String use_consume = listConsume.get(i).getText().toString();
                                    materials.setIngredient(use_ingredient);
                                    materials.setConsumption(use_consume);
                                    Post post1=new Post();
                                    materials.setId(post1);

                                }
                            }

                                Method method = new Method();
                                Post post1 = new Post();
                                method.setId(post1);
                                String sequence = process1.getText().toString();
                                String interpret1 = explain1.getText().toString();
                                String imageUrl = stringMap.get(image1);
                                method.setSequence(sequence);
                                method.setImage(imageUrl);
                                method.setExplain(interpret1);


                            if(linear2.getVisibility()!=View.GONE){
                                Method method1=new Method();
                                Post post2=new Post();
                                method1.setId(post2);
                                String two_sequence=process2.getText().toString();
                                String image_url=stringMap.get(image2);
                                String interpret2=explain2.getText().toString();
                                method1.setSequence(two_sequence);
                                method1.setImage(image_url);
                                method1.setExplain(interpret2);

                            }
                            if(listProcess.size()!=0) {
                                for(int i=0;i<listProcess.size();i++){
                                    Method method2 = new Method();
                                    Post post3 = new Post();
                                    method2.setId(post3);
                                    String more_sequence=listProcess.get(i).getText().toString();
                                    String more_image=stringMap.get(listImage.get(i));
                                    String more_explain=listExplain.get(i).getText().toString();
                                    method2.setSequence(more_sequence);
                                    method2.setImage(more_image);
                                    method2.setExplain(more_explain);

                                }
                            }
//                    Thread.sleep(5000);
                            String path="http://mdzs.free.svipss.top/publish";
                            URL url = new URL(path);
                            //打开httpurlconnection
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");              //设置POST方式获取数据
                            conn.setConnectTimeout(5000);              //设置连接超时时间5秒
                            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  //如果设置方式为post，则必须制定该属性
                            //将数据进行编码,然后会自动的将该数据放到post中传到后台
                            String data="post="+ URLEncoder.encode(post.toString(),"utf-8")+"&materials="+ URLEncoder.encode(materials.toString(),"utf-8");
//                            String data="userID="+ URLEncoder.encode("123456","utf-8")+"&userPwd="+URLEncoder.encode("123456","utf-8")+"&userName="+URLEncoder.encode("123456","utf-8")+"&phoneNumber="+URLEncoder.encode("123456","utf-8");
                            //指定长度
                            conn.setRequestProperty("Content-length",String.valueOf(data.length()));
                            conn.setDoOutput(true); //指定输出模式
                            conn.getOutputStream().write(data.getBytes());  //将要传递的数据写入输出流
                            int code = conn.getResponseCode();         // 获取response状态，200表示成功获取资源，404表示资源不存在
                            if (code==200){
                                InputStream is=conn.getInputStream();
                                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                                StringBuffer sb=new StringBuffer();
                                String len=null;
                                while((len=br.readLine())!=null){
                                    sb.append(len);
                                }
                                String result=sb.toString();

//                                showMsg(result);
                                Log.d("登陆", result);
                                Intent intent=new Intent(DynamicActivity.this,ShowActivity.class);
                                intent.putExtra("objectId",p_objectId);
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("Activity", "cuowu");
//                            showMsg("不成功");
                        }
                    }
                }.start();

                isPublish=false;
        }
    }

    /**
     * 检查版本
     */
    private void checkVersion() {
        //Android6.0及以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果你是在Fragment中，则把this换成getActivity()
            rxPermissions = new RxPermissions(this);
        } else {
            //Android6.0以下
            showMsg("无需请求动态权限");
        }
    }

    /**
     * 打开相册
     */
    private void openAlbum(ImageButton imageView) {
        tmp_image=imageView;

        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        startActivityForResult(CameraUtils.getSelectPhotoIntent(), SELECT_PHOTO);
    }


    /**
     * 返回到Activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //打开相册后返回
            case SELECT_PHOTO:
                if (resultCode == -1) {
                    String imagePath="";
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePath = CameraUtils.getImageOnKitKatPath(data, this);
                    } else {
                        imagePath = CameraUtils.getImageBeforeKitKatPath(data, this);
                    }

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

            stringMap.put(tmp_image,imagePath);

            //显示图片
            Glide.with(this).load(imagePath).into(tmp_image);

            //压缩图片
            orc_bitmap = CameraUtils.compression(BitmapFactory.decodeFile(imagePath));
            //转Base64
            base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);

        } else {
            showMsg("图片获取失败");
        }
    }



    /**
     * Toast提示
     *
     * @param msg
     */
    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void delBtn(View view) {//动态删除按钮
        if (view == null) {
            return;
        }
        int position = -1;
        for (int i = 0; i < listDel.size(); i++) {
            if (listDel.get(i) == view) {
                position = i;
                break;
            }
        }
        if (position >= 0) {
            listIngredient.remove(position);
            listDel.remove(position);
            listMove.remove(position);
            listConsume.remove(position);
            linearLayout.removeViewAt(position + 1);//在外出linearLayout删除内部指定位置所有控件
        }
    }

    private void delProcess(View view) {//动态删除按钮
        if (view == null) {
            return;
        }
        int position = -1;
        for (int i=0; i < listDelProcess.size(); i++) {
            if (listDelProcess.get(i) == view) {
                position = i;
                break;
            }
        }
        if (position >= 0) {
            listDelProcess.remove(position);
            listMoveProcess.remove(position);
            listExplain.remove(position);
            listImage.remove(position);
            listProcess.remove(position);
            processLayout.removeViewAt(position + 1);//在外出linearLayout删除内部指定位置所有控件
        }
        for(TextView view1:listProcess.subList(position,listProcess.size())){
            view1.setText("步骤"+(n+position+1));
            position++;
        }

    }

    private void showNum(int n){
        int position=0;
        for(TextView view1:listProcess){
            view1.setText("步骤"+(n+position+1));
            position++;
        }
    }

    private void add(String name){
        LinearLayout linear_btn = new LinearLayout(this);
        linear_btn.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams liParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        linear_btn.setLayoutParams(liParam);

        ImageView del=new ImageView(this);
        LinearLayout.LayoutParams delPar = new LinearLayout.LayoutParams
                (60, 60 );
        delPar.setMargins(10,5,5,5);
        delPar.gravity= Gravity.CENTER;
        del.setLayoutParams(delPar);
        del.setImageResource(R.drawable.del);
        del.setBackgroundColor(Color.argb(255, 255, 0, 0));
        del.setVisibility(View.GONE);
        del.setClickable(true);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delBtn(v);
            }
        });
        linear_btn.addView(del);
        listDel.add(del);

        EditText ingredient=new EditText(this);
        LinearLayout.LayoutParams addPar = new LinearLayout.LayoutParams
                (0,ViewGroup.LayoutParams.WRAP_CONTENT,2);
        addPar.setMargins(6,6,6,6);
        ingredient.setLayoutParams(addPar);
        ingredient.setBackground(null);
        if(name.equals("食材：如鸡蛋")) {
            ingredient.setHint(name);
        }else{
            ingredient.setText(name);
        }
        linear_btn.addView(ingredient);
        listIngredient.add(ingredient);

        EditText consume=new EditText(this);
        LinearLayout.LayoutParams consumePar = new LinearLayout.LayoutParams
                (0,ViewGroup.LayoutParams.WRAP_CONTENT,2);
        consumePar.setMargins(6,6,6,6);
        consume.setLayoutParams(consumePar);
        consume.setBackground(null);
        if(name.equals("食材：如鸡蛋")) {
            consume.setHint(map.get(name));
        }else{
            consume.setText(map.get(name));
        }
        linear_btn.addView(consume);
        listConsume.add(consume);

        ImageView move=new ImageView(this);
        LinearLayout.LayoutParams movePar = new LinearLayout.LayoutParams
                (60, 60 );
        movePar.setMargins(10,5,5,5);
        movePar.gravity= Gravity.CENTER;
        move.setLayoutParams(movePar);
        move.setImageResource(R.drawable.move);
        move.setVisibility(View.GONE);
        move.setClickable(true);
        linear_btn.addView(move);
        listMove.add(move);

        linearLayout.addView(linear_btn);
    }

}