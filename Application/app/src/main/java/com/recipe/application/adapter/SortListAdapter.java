package com.recipe.application.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipe.application.R;
import com.recipe.application.activity.ShowActivity;
import com.recipe.application.dao.Post;
import com.recipe.application.utils.MyBitmapUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SortListAdapter extends BaseAdapter {

    private List<Post> data;

    private Context context;

    public SortListAdapter(Context context, List<Post> data) {
        this.context = context;
        this.data = data;
    }

    //设置图片圆角角度
    RoundedCorners roundedCorners= new RoundedCorners(6);
    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .skipMemoryCache(false);
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
        Post group = data.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_msg_item, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(group.getName());
        holder.author.setText(group.getPeople());
        holder.introduction.setText(group.getIntroduction());
        //MyBitmapUtils myBitmapUtils=new MyBitmapUtils();
        //myBitmapUtils.disPlay(holder.img,group.getCover());
        Glide.with(context).load(group.getCover()).apply(requestOptions).into(holder.img);
        return convertView;
    }

    private class ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView introduction;
        private TextView author;

        public ViewHolder(View view) {
            img=view.findViewById(R.id.img);
            name=view.findViewById(R.id.name);
            introduction=view.findViewById(R.id.introduction);
            author=view.findViewById(R.id.author);
        }
    }


}
