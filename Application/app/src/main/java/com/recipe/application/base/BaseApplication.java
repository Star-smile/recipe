package com.recipe.application.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.WindowManager;

public class BaseApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public static Resources sResource;

    //private static final String PREF_NAME="creativelocker.pref";
    //private static final String LAST_REFRESH_TIME="last_refresh_time.pref";
    public static boolean isAtLeastGB;

    static{
        isAtLeastGB=true;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        context=getApplicationContext();
        sResource=context.getResources();
    }

    public static synchronized BaseApplication context(){
        return (BaseApplication)context;
    }

    public static Context getContext() {
        return context;
    }


    /*
    public static Resources resources(){
        return sResource;
    }*/


}
