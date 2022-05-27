package com.recipe.application.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.recipe.application.R;
import com.recipe.application.adapter.SortListAdapterGV;
import com.recipe.application.cache.CacheHelper;
import com.recipe.application.cache.ReadCacheAsyncTask;
import com.recipe.application.cache.SaveCacheAsyncTask;
import com.recipe.application.dao.Brief_dish;
import com.recipe.application.dao.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class FindFragment extends BasePutToRefreshFragment<SortListAdapterGV>{

    private SortListAdapterGV mSortListAdapter;

    @Override
    public View getRootView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_find, null, false);
    }

    @Override
    public SortListAdapterGV getAdapter() {
        mSortListAdapter = new SortListAdapterGV(getContext());
        return mSortListAdapter;
    }

    @Override
    public void readCache() {
        readCache(CacheHelper.GROUP_LIST_CACHE_KEY);
    }

    @Override
    public void requestDataByNet(int actionType) {
        putToRefreshByUnit();
    }

    @Override
    public void initArguments() {

    }

    @Override
    public void createViewsOrListener() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * “单元”列表下拉刷新具体实现
     */
    private void putToRefreshByUnit() {
        List list=new ArrayList();
                    requestPointByUnits(list);
                }

    /**
     * 初始化页面
     */
    private void requestPointByUnits(final List<Type> typeList) {
        //初始化Adapter数据结构
        final List<Map<String, List<Brief_dish>>> listGroup = new ArrayList<>();

                    //更新Adapter
                    mAdapter.notifyAdapter(listGroup);
                    if (listGroup.size() != 0) {
                        //把数据缓存到本地
                        SaveCacheAsyncTask savecaheTask = new SaveCacheAsyncTask(getContext(), (Serializable) listGroup, CacheHelper.GROUP_LIST_CACHE_KEY);
                        savecaheTask.execute();
                    }
                    //更新UI
                    mPtrFrameLayout.setVisibility(View.VISIBLE);
                    mPtrFrameLayout.refreshComplete();
                }



    /**
     * 读取缓存
     *
     * @param cacheKey
     */
    private void readCache(String cacheKey) {
        ReadCacheAsyncTask<List<Map<String, List<Brief_dish>>>> readCache = new ReadCacheAsyncTask<>(getContext());
        readCache.setOnReadCacheToDo(new ReadCacheAsyncTask.OnReadCacheToDo<List<Map<String, List<Brief_dish>>>>() {
            @Override
            public void preExecute() {
                mPtrFrameLayout.setVisibility(View.GONE);
            }

            @Override
            public void postExectue(List<Map<String, List<Brief_dish>>> data) {
                if (data == null || data.size() == 0) {
                    mPtrFrameLayout.autoRefresh(true);
                } else {
                    mPtrFrameLayout.setVisibility(View.VISIBLE);
                    mSortListAdapter.setDatas(data);
                }

            }
        });
        readCache.execute(cacheKey);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPtrFrameLayout!=null&&mPtrFrameLayout.isRefreshing()){
            mPtrFrameLayout.refreshComplete();
        }
    }

    private void packetAdapterData(List<Brief_dish> dishList, List<Type> typeList, List<Map<String, List<Brief_dish>>> listGroup) {
        //根据所有单元，组合好以Map<String,List<Point>> 形式的View以便adapter好处理
        for (Type type:typeList) {
            Map<String, List<Brief_dish>> map = new HashMap<>();
            List<Brief_dish> dishesForType = new ArrayList<Brief_dish>();
            for (Brief_dish dish:dishList) {
                if (dish.getType_id().toString().equals(type.toString())) {
                    dishesForType.add(dish);
                }
            }

            //如果无数据，则插入一个空数据用于 友好提示
            if (dishesForType.size() == 0) {
                Brief_dish dish=new Brief_dish();
                dish.setDish_name("暂无内容\n敬请期待");
                dishesForType.add(dish);
            }
            map.put(type.getType_name(), dishesForType);
            listGroup.add(map);
        }
    }

}
