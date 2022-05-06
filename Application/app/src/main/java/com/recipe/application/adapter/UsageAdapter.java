package com.recipe.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipe.application.R;
import com.recipe.application.dao.Method;
import com.recipe.application.utils.MyBitmapUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class UsageAdapter extends BaseAdapter {

    //设置图片圆角角度
    RoundedCorners roundedCorners= new RoundedCorners(6);
    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .skipMemoryCache(false);


    List<Method> data;
    private Context context;

    public UsageAdapter(Context context,List<Method> data){
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
        Method method=data.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_usage,null,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.sequence.setText(method.getSequence());
        viewHolder.explain.setText(method.getExplain());
        //MyBitmapUtils myBitmapUtils=new MyBitmapUtils();
        //myBitmapUtils.disPlay(viewHolder.image, method.getImage());
        //显示图片
        Glide.with(context).load(method.getImage()).apply(requestOptions).into(viewHolder.image);
        return convertView;
    }

    private class ViewHolder{
        private TextView sequence;
        private ImageView image;
        private TextView explain;

        private ViewHolder(View view){
            sequence=view.findViewById(R.id.sequence);
            image=view.findViewById(R.id.image);
            explain=view.findViewById(R.id.explain);
        }
    }
}

