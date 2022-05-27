package com.recipe.application.cache;


import android.content.Context;
import android.content.SharedPreferences;
import com.recipe.application.base.Settings;
import com.recipe.application.utils.TDevice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 缓存帮助实现类
 */
public class CacheHelper {

    public final static String FAV = "fav.pref";
    public final static String GROUP_LIST_CACHE_KEY = "group_list";
    public final static String CONTENT_LIST_CACHE_KEY = "content_list_";
    public final static String CONTENT_CACHE_KEY = "content_";
    public final static String TEST = "test_";
    private static com.recipe.application.base.AppContext AppContext;

    /**
     * 调用SharedPreferences存储方法，获取SharedPreferences对象
     * @param prefName
     * @return
     */
    public static SharedPreferences getPreferences(String prefName) {
        return AppContext.getInstance().getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    /**
     * 从写入的文件中通过键获取值
     * @param key 要查询的键
     * @return
     */
    public static long getFav(String key) {
        return getPreferences(FAV).getInt(key, -1);
    }

    /**
     * 更改后，通过apply函数进行提交
     * @param editor
     */
    private static void apply(SharedPreferences.Editor editor) {
        if (AppContext.isAtLeastGB) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    /**
     * 向文件中写入键值对，并提交
     * @param key 键
     * @param value 值
     */
    public static void putToFav(String key, int value) {
        SharedPreferences preferences = getPreferences(FAV);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        apply(editor);
    }

    /**
     * 从文件中通过键删除值，并提交
     * @param key 键
     */
    public static void removeToFav(String key){
        SharedPreferences preferences=getPreferences(FAV);
        SharedPreferences.Editor editor=preferences.edit();
        editor.remove(key);
        apply(editor);
    }

    /**
     * 保存对象
     * @param context
     * @param ser
     * @param file
     * @return
     */
    public static boolean saveObject(Context context, Serializable ser,String file){
        FileOutputStream fos=null;
        ObjectOutputStream obj=null;
        try{
            fos=context.openFileOutput(file,Context.MODE_PRIVATE);
            obj= new ObjectOutputStream(fos);
            obj.writeObject(ser);
            obj.flush();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            try{
                obj.close();
            }catch(Exception e){
                e.printStackTrace();
            }try{
                fos.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取对象
     * @param context
     * @param file
     * @return
     */
    public static Serializable readObject(Context context,String file){

        if(!isExistDataCache(context, file)){
            return null;
        }
        FileInputStream fis=null;
        ObjectInputStream obj=null;
        try{
            fis=context.openFileInput(file);
            obj=new ObjectInputStream(fis);
            return (Serializable)obj.readObject();
        }catch(Exception e){
            e.printStackTrace();
            if(e instanceof InvalidClassException){
                File data=context.getFileStreamPath(file);
                data.delete();
            }
        }finally{
            try{
                obj.close();
            }catch(Exception e){
                e.printStackTrace();
            }try{
                fis.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 判断缓存是否存在
     * @param context
     * @param cacheFile
     * @return
     */
    public static boolean isExistDataCache(Context context,String cacheFile){
        if(context==null){
            return false;
        }
        boolean exist=false;
        File data=context.getFileStreamPath(cacheFile);
        if(data.exists()){
            exist=true;
        }
        return exist;
    }

    /**
     * 判断缓存是否失效
     * @param context
     * @param cacheFile
     * @return
     */
    public static boolean isCacheDataFailure(Context context,String cacheFile){
        File data=context.getFileStreamPath(cacheFile);
        if(!data.exists()){
            return false;
        }
        long existTime=System.currentTimeMillis()-data.lastModified();
        boolean failure=false;
        if(TDevice.getNetworkType()==TDevice.NETTYPE_WIFI){
            failure = existTime > Settings.getInt(Settings.CACHE_OVERTIME_WIFI, 30) * 60 * 1000;
        } else {
            failure = existTime > Settings.getInt(Settings.CACHE_OVERTIME_OTHER, 2) * 24 * 60 * 60 * 1000;
        }
        return failure;
    }

    /**
     * 开启缓存过期时间
     * @return
     */
    public static boolean isOpenCacheOverTime(){
        return Settings.getBoolean(Settings.CACHE_OVERTIME,false);
    }
}

