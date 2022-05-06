package com.recipe.application.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.recipe.application.R;
import com.recipe.application.dao.Materials;

import java.util.List;

public class MaterialsAdapter extends BaseAdapter {

    List<Materials> data;

    private Context context;

    public MaterialsAdapter(Context context,List<Materials> data){
        this.data=data;
        this.context=context;
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
        Materials materials=data.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_materials,null,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.ingredient.setText(materials.getIngredient());
        viewHolder.consumption.setText(materials.getConsumption());

        return convertView;
    }

    private class ViewHolder{
        private TextView ingredient;
        private TextView consumption;

        private ViewHolder(View view){
            ingredient=view.findViewById(R.id.ingredient);
            consumption=view.findViewById(R.id.consumption);
        }
    }
}

