package com.recipe.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.recipe.application.R;
import com.recipe.application.dao.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class FanAdapter extends BaseAdapter {

    private List<User> data;
    private Context context;

    public FanAdapter(Context context, List<User> data){
        this.context=context;
        this.data=data;
    }

    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user=data.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_fan,null,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.takeName.setText(user.getName());
        Glide.with(context).load(user.getImage()).apply(requestOptions).into(viewHolder.iv_head);
        return convertView;
    }

    private class ViewHolder{
        private ShapeableImageView iv_head;
        private TextView takeName;

        public ViewHolder(View view){
            iv_head=view.findViewById(R.id.iv_head);
            takeName=view.findViewById(R.id.take_name);
        }

    }
}

