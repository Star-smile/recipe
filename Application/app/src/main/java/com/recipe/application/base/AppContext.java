package com.recipe.application.base;
import androidx.multidex.MultiDex;


public class AppContext extends BaseApplication{
    private static AppContext instance;

    private static final String ApplicationID="8b1ab0025dbedc9236119beeba989fa1";

    @Override
    public void onCreate(){
        super.onCreate();
        instance=this;
        MultiDex.install(this);
//        Bmob.initialize(this, "d8c5c191055ce4708b5a305abc10e4a6");
    }

    public static AppContext getInstance(){
        return instance;
    }
}