package com.llw.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.llw.recipe.MainActivity;
import com.llw.recipe.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;



public class RegisterActivity extends AppCompatActivity {

    private EditText account;
    private EditText password;
    private EditText sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register = findViewById(R.id.register);

        account=findViewById(R.id.account);
        password=findViewById(R.id.password);
        sure=findViewById(R.id.sure);




        //注册界面
        register.setOnClickListener(v -> {
            String accountName=account.getText().toString();
            String passwordNum=password.getText().toString();
            String pwd=sure.getText().toString();

            if(!passwordNum.equals(pwd)){
                Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
            }

            //注册接口
            new Thread(){
                @Override
                public void run() {
                    try {
//                    Thread.sleep(5000);
                        String path="http://mdzs.free.svipss.top/login?";
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

//                                showMsg(result);
                            Log.d("登陆", result);
                            if(result.equals(false)){
                                Toast.makeText(RegisterActivity.this,"用户已存在",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("ZhuCeActivity", "cuowu");
//                            showMsg("不成功");
                    }
                }
            }.start();


        });
    }

}
