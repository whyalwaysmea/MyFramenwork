package com.ithaha.myframework.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by HelloWorld on 2016/6/12.
 */
public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {

    private Application application;
    private static AppStatusTracker appStatusTracker;

    private AppStatusTracker(Application application) {
        this.application = application;
//        application.registerActivityLifecycleCallbacks(this);
    }

    public static AppStatusTracker getInstance(Application application) {
        if(appStatusTracker == null) {
            appStatusTracker = new AppStatusTracker(application);
        }
        return appStatusTracker;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(activity.getLocalClassName(), "onCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(activity.getLocalClassName(), "onStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(activity.getLocalClassName(), "onResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(activity.getLocalClassName(), "onPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(activity.getLocalClassName(), "onStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(activity.getLocalClassName(), "onDestroyed");
    }
}
