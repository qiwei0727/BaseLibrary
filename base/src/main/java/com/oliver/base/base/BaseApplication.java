package com.oliver.base.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

/**
 * description：//todo
 * author：oliver
 * email：qiwei0727@163.com
 * date：on 2017/11/18
 */

public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;
    private static Stack<Activity> mActivityStack;

    /**
     * 获取当前Application
     * @return
     */
    public static BaseApplication get() {
        return mBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;

        mActivityStack = new Stack<>();

        //监听activity的生命周期
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mActivityStack.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                mActivityStack.remove(activity);
            }
        });
    }


    /**
     * 获取当前Activity
     *
     * @return
     */
    public static Activity getCurrentActivity() {
        return mActivityStack.lastElement();
    }

    /**
     * 退出App
     */
    public static void exitApp() {
        for (Activity a : mActivityStack) {
            a.finish();
        }

    }
}
