package com.ithaha.myframework.base;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by HelloWorld on 2016/4/26.
 */
public class MyApplication extends Application {

    public static ArrayList<String> sMyData;
    public static int sAppStatus = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyData = new ArrayList<>();
        AppStatusTracker.getInstance(this);
    }
}
