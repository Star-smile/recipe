package com.recipe.application.cache;


import android.content.Context;
import android.os.AsyncTask;

import java.io.Serializable;
import java.lang.ref.WeakReference;

/**
 * 读取缓存
 * 实现原理：先定义一个类继承自AsyncTask，采用异步消息处理机制，并规定在执行AsyncTask，传入参数为String类型，
 * 在后台执行任务时，不需要在界面显示当前进度，执行结束返回泛型T,并重写了其中的onPreExecute，doInBackground，
 * onPostExecute函数，同时在类内部定义了OnReadCacheToDo接口。
 * @param <T>
 */
public class ReadCacheAsyncTask<T> extends AsyncTask<String, Void, T> {
    private WeakReference<Context> mContext;

    public interface OnReadCacheToDo<T> {
        void preExecute();

        void postExectue(T data);
    }

    private OnReadCacheToDo<T> onReadCacheToDo;

    public void setOnReadCacheToDo(OnReadCacheToDo<T> onReadCacheToDo) {
        this.onReadCacheToDo = onReadCacheToDo;
    }

    public ReadCacheAsyncTask(Context context) {
        mContext = new WeakReference<Context>(context);
    }

    /**
     * 初始化操作
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onReadCacheToDo != null) {
            onReadCacheToDo.preExecute();
        }
    }

    /**
     * 在后台处理后事业务，进行对象的读取
     * @param params
     * @return
     */
    @Override
    protected T doInBackground(String... params) {
        if (mContext.get() != null) {
            Serializable seri = CacheHelper.readObject(mContext.get(), params[0]);
            if (seri == null) {
                return null;
            } else {
                return (T) seri;
            }
        }
        return null;
    }

    /**
     * 对整个读取过程就行收尾操作
     * @param data
     */
    @Override
    protected void onPostExecute(T data) {
        super.onPostExecute(data);
        if (onReadCacheToDo != null) {
            onReadCacheToDo.postExectue(data);
        }
    }
}

