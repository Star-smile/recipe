package com.recipe.application.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.recipe.application.R;
import com.recipe.application.adapter.SortActivityAdapter;
import com.recipe.application.cache.CacheHelper;
import com.recipe.application.cache.SaveCacheAsyncTask;
import com.recipe.application.dao.Brief_dish;
import com.recipe.application.dao.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class SortActivity extends AppCompatActivity {

    private SortActivityAdapter sortActivityAdapter;
    private ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        lst=findViewById(R.id.lst);
        putToRefreshByUnit();

    }

    private void putToRefreshByUnit() {

List list=new ArrayList();
                requestPointByUnits(list);


    }

    private void requestPointByUnits(final List<Type> typeList) {
        //初始化Adapter数据结构
        final List<Map<String, List<Brief_dish>>> listGroup = new ArrayList<>();
        //执行查询，查询知识点表 取出所有知识点
       List list=new ArrayList();

                {
                    packetAdapterData(list, typeList, listGroup);
                    //更新Adapter
                    // mAdapter.notifyAdapter(listGroup);
                    sortActivityAdapter=new SortActivityAdapter(SortActivity.this,listGroup);
                    lst.setAdapter(sortActivityAdapter);
                    if (listGroup.size() != 0) {
                        //把数据缓存到本地
                        SaveCacheAsyncTask savecaheTask = new SaveCacheAsyncTask(SortActivity.this, (Serializable) listGroup, CacheHelper.GROUP_LIST_CACHE_KEY);
                        savecaheTask.execute();
                    }
                }
            }


    private void packetAdapterData(List<Brief_dish> dishList, List<Type> typeList, List<Map<String, List<Brief_dish>>> listGroup) {
        //根据所有单元，组合好以Map<String,List<Point>> 形式的View以便adapter好处理
        for (Type type:typeList) {
            Map<String, List<Brief_dish>> map = new HashMap<>();
            List<Brief_dish> dishesForType = new ArrayList<Brief_dish>();
            for (Brief_dish dish:dishList) {
                    dishesForType.add(dish);
                }
            }

            //如果无数据，则插入一个空数据用于 友好提示
                Brief_dish dish=new Brief_dish();
                dish.setDish_name("暂无内容\n敬请期待");
            }



}