package com.recipe.application.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.recipe.application.R;
import com.recipe.application.dao.Brief_dish;
import com.recipe.application.utils.MyBitmapUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SortActivityAdapter extends BaseAdapter {


    List<Map<String, List<Brief_dish>>> data;

    private Context context;

    public SortActivityAdapter(Context context,List<Map<String, List<Brief_dish>>> data) {
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
        Map<String, List<Brief_dish>> group = data.get(position);
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_review_item_gv, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String typeName="";
        if(group.keySet().iterator().hasNext()){
            typeName=group.keySet().iterator().next();
        }
        holder.tv_unit.setText(typeName);
        List<Brief_dish> dishes=group.get(typeName);
        holder.gv_carview.setAdapter(new GvAdapter(dishes));
        holder.gv_carview.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return convertView;

    }

    /**
     * ??????????????????????????????
     */
    private class ViewHolder {
        private TextView tv_unit;
        private GridView gv_carview;

        public ViewHolder(View view) {

            tv_unit = view.findViewById(R.id.tv_unit);
            gv_carview =  view.findViewById(R.id.gv_carview);

        }
    }

    private class GvAdapter extends BaseAdapter {

        private List<Brief_dish> brief_dishes = new ArrayList<>();

        private GvAdapter(List<Brief_dish> brief_dishes) {
            this.brief_dishes = brief_dishes;
        }

        @Override
        public int getCount() {
            return brief_dishes.size();
        }

        @Override
        public Object getItem(int position) {
            return brief_dishes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("HandlerLeak")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Brief_dish dish = brief_dishes.get(position);
            ViewHolder_GV viewHolder_gv = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.carview_review, null);
                viewHolder_gv = new ViewHolder_GV(convertView);
                convertView.setTag(viewHolder_gv);

            } else {
                viewHolder_gv = (ViewHolder_GV) convertView.getTag();
            }

            viewHolder_gv.tv_carview.setText(dish.getDish_name());
            MyBitmapUtils myBitmapUtils=new MyBitmapUtils();
            myBitmapUtils.disPlay(viewHolder_gv.tv_imageView, dish.getImage());
            return convertView;
        }
    }

    /**
     * ?????????????????????????????????????????????
     */
    private class ViewHolder_GV {
        private TextView tv_carview;
        private ImageView tv_imageView;

        public ViewHolder_GV(View view) {
            tv_carview = view.findViewById(R.id.tv_carview);
            tv_imageView=view.findViewById(R.id.tv_image);
        }

    }

}


