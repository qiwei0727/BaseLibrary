package com.oliver.baselibrary.base;

import android.app.Application;

/**
 * description：//todo
 * author：oliver
 * email：qiwei0727@163.com
 * date：on 2017/11/18
 */

public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;

    public static BaseApplication get() {
        return mBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
    }
}
