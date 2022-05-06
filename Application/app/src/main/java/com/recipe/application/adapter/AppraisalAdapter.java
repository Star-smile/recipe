package com.recipe.application.adapter;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.recipe.application.R;
import com.recipe.application.dao.Comment;
import com.recipe.application.dao.User;
import com.recipe.application.utils.MyBitmapUtils;
import com.recipe.application.utils.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;


public class AppraisalAdapter extends BaseAdapter {

    List<Comment> data;

    private Context context;

    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(false);//不做内存缓存

    public AppraisalAdapter(Context context, List<Comment> data){
        this.context=context;
        this.data=data;
    }

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

        final User user = new User();
        Comment comment=data.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_appraisal,null,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(comment.getName());
        viewHolder.content.setText(comment.getContent());
        viewHolder.time.setText(comment.getTime());
        //显示图片
        Glide.with(context).load(comment.getImage()).apply(requestOptions).into(viewHolder.image);

        if(comment.getName().equals(user.getName())){
            viewHolder.del.setVisibility(View.VISIBLE);
        }
        viewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objectId=comment.getId().toString();
                Comment comment1=new Comment();

            }
        });

        return convertView;
    }

    private class ViewHolder{
        private ShapeableImageView image;
        private TextView name;
        private TextView time;
        private TextView content;
        private TextView del;

        private ViewHolder(View view){
            image=view.findViewById(R.id.use_image);
            name=view.findViewById(R.id.use_name);
            time=view.findViewById(R.id.c_time);
            content=view.findViewById(R.id.content);
            del=view.findViewById(R.id.del);
        }
    }
}

