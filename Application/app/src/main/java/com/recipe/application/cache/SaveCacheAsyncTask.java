package com.recipe.application.cache;


import android.content.Context;
import android.os.AsyncTask;

import java.io.Serializable;
import java.lang.ref.WeakReference;

/**
 * 将数据缓存到本地
 * 实现原理：先定义一个类继承自AsyncTask，采用异步消息处理机制，并规定在执行AsyncTask，无传入类型，无返回类型
 * 并重写了doInBackground，添加了类的构造方法
 */
public class SaveCacheAsyncTask extends AsyncTask<Void, Void, Void> {
    private WeakReference<Context> mContext;
    private Serializable seri;
    private String cacheKey;

    public SaveCacheAsyncTask(Context context, Serializable seri, String cacheKey) {
        mContext = new WeakReference<Context>(context);
        this.seri = seri;
        this.cacheKey = cacheKey;
    }

    @Override
    protected Void doInBackground(Void... params) {
        CacheHelper.saveObject(mContext.get(), seri, cacheKey);
        return null;
    }


}

