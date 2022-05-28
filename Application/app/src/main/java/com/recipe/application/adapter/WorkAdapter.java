package com.recipe.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.recipe.application.R;
import com.recipe.application.dao.Focus;
import com.recipe.application.dao.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;




public class WorkAdapter {

    private List<User> data;

    private Context context;

    public WorkAdapter(Context context, List<User> data) {
        this.context = context;
        this.data = data;
    }

    //设置图片圆角角度
    RoundedCorners roundedCorners= new RoundedCorners(6);
    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .skipMemoryCache(false);

    public int getCount() {
        return data.size();
    }


    public Object getItem(int position) {
        return data.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        User user=data.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_work,null,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.takeName.setText(user.getName());
        Glide.with(context).load(user.getImage()).apply(requestOptions).into(viewHolder.iv_head);
        viewHolder.focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean tmp=true;
                if(tmp){
                    tmp=!tmp;
                    viewHolder.focus.setText("取消关注");
                    final User users=new User();
                    String who=users.getName();
                    String name=user.getName();
                    Focus focus1=new Focus();


                }else{
                    tmp=!tmp;
                    viewHolder.focus.setText("关注");
                    final User users=new User();
                    String who=users.getName();
                    String name=user.getName();
                    Focus focus=new Focus();
                    focus.setName(name);
                    focus.setWho(who);

                }
            }
        });
        return convertView;
    }

    private class ViewHolder{
        private ShapeableImageView iv_head;
        private TextView takeName;
        private Button focus;

        public ViewHolder(View view){
            iv_head=view.findViewById(R.id.iv_head);
            takeName=view.findViewById(R.id.take_name);
            focus=view.findViewById(R.id.focus);
        }

    }
}
