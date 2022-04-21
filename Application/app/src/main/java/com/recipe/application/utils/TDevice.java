package com.recipe.application.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.android.recipe.base.AppContext;
import com.android.recipe.base.BaseApplication;

import java.io.File;

public class TDevice {
    //手机网络类型
    public static final int NETTYPE_WIFI=0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public static boolean GTE_HC;
    public static boolean GTE_ICS;
    public static boolean PRE_HC;
    private static Boolean _hasBigScreen = null;
    private static Boolean _hasCamera = null;
    private static Boolean _isTablet = null;
    private static Integer _loadFactor = null;

    private static int _pageSize = -1;
    public static float displayDensity = 0.0F;

    static {
        GTE_ICS = Build.VERSION.SDK_INT >= 14;
        GTE_HC = Build.VERSION.SDK_INT >= 11;
        PRE_HC = Build.VERSION.SDK_INT < 11;
    }

    public TDevice(){}

    public static float dpToPixel(float dp){
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }

    public static DisplayMetrics getDisplayMetrics(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager)BaseApplication.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

    /**
     * 获取屏幕的高度
     * @return
     */

    public static float getDensity(){
        if(displayDensity==0.0)
            displayDensity=getDisplayMetrics().density;
        return displayDensity;
    }

    /**
     * 获取屏幕的宽度
     * @return
     */

    @SuppressLint("WrongConstant")
    public static boolean hasInternet(){
        boolean flag;
        if(((ConnectivityManager)BaseApplication.context().getSystemService("connectivity")).getActiveNetworkInfo()!=null){
            flag=true;
        }else{
            flag=false;
        }
        return flag;
    }

    public static Intent getInstallApkIntent(File file) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        return intent;
    }

    public static void hideSoftKeyboard(View view){
        if (view == null)
            return;
        ((InputMethodManager) BaseApplication.context().getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                view.getWindowToken(), 0);
    }

    public static void toogleSoftKeyboard(View view) {
        ((InputMethodManager) BaseApplication.context().getSystemService(
                Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 获取当前网络类型
     * 0 没有网络
     * 1 WIFI网络
     * 2 WAP网络
     * 3 NET网络
     * @return
     */
    public static int getNetworkType() {
        int netType = 0;
        ConnectivityManager manager = (ConnectivityManager) AppContext
                .getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return netType;
        }
        int type = info.getType();
        if (type == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = info.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }
}

