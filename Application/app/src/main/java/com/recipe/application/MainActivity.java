package com.recipe.application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.recipe.application.activity.PrimaryActivity;
import com.recipe.application.activity.RegisterActivity;
import com.recipe.application.activity.ResetActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private CheckBox remember;

    private EditText account;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        account=findViewById(R.id.account);
        password=findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button secret=findViewById(R.id.secret);
        //可以选择是否记住密码
        remember=findViewById(R.id.remember);
        Button register = findViewById(R.id.register);
        boolean isRemember=preferences.getBoolean("remember_password",false);
        if(isRemember){
            String accountName=preferences.getString("account","");
            String passwordNum=preferences.getString("password","");
            account.setText(accountName);
            password.setText(passwordNum);
            remember.setChecked(true);
        }

        register.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        //  登录点击监听事件
        login.setOnClickListener(v -> {
            String accountName=account.getText().toString();
            String passwordNum=password.getText().toString();

//            BmobUser bu2 = new BmobUser();
//            bu2.setUsername(accountName);
//            bu2.setPassword(passwordNum);
//
//            //登录判断逻辑
//            bu2.login(new SaveListener<BmobUser>() {
//                @Override
//                public void done(BmobUser bmobUser, BmobException e) {
//                    if(e==null){
//                        editor=preferences.edit();
//                        if(remember.isChecked()){
//                            editor.putBoolean("remember_password",true);
//                            editor.putString("account",accountName);
//                            editor.putString("password",passwordNum);
//                        }else{
//                            editor.clear();
//                        }
//                        editor.apply();
//                        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(MainActivity.this, PrimaryActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }else{
//                        Toast.makeText(MainActivity.this,"登录失败,账号或者密码错误",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            });
            new Thread(){
                @Override
                public void run() {
                    try {
//                    Thread.sleep(5000);
//                            String path="http://47.113.226.119:8080/register";
                        String path="http://mdzs.free.svipss.top/login";

                        URL url = new URL(path);
                        //打开httpurlconnection
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");              //设置POST方式获取数据
                        conn.setConnectTimeout(5000);              //设置连接超时时间5秒
                        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  //如果设置方式为post，则必须制定该属性
                        //将数据进行编码,然后会自动的将该数据放到post中传到后台
                        String data="userID="+ URLEncoder.encode(accountName,"utf-8")+"&userPwd="+ URLEncoder.encode(passwordNum,"utf-8");
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
                            if(result.equals(true)){
                                editor=preferences.edit();
                                if(remember.isChecked()){
                                    editor.putBoolean("remember_password",true);
                                    editor.putString("account",accountName);
                                    editor.putString("password",passwordNum);
                                }else{
                                    editor.clear();
                                }
                                editor.apply();
                                Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MainActivity.this, PrimaryActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(MainActivity.this,"登录失败,账号或者密码错误",Toast.LENGTH_SHORT).show();
                            }

//                                showMsg(result);
                            Log.d("登陆", result);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("ZhuCeActivity", "cuowu");

//                            showMsg("不成功");


                    }
                }
            }.start();



        });

        secret.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, ResetActivity.class);
            startActivity(intent);
        });
    }
}