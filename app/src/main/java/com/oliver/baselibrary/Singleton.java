package com.oliver.baselibrary;

/**
 * description：//通过静态内部类实现单例
 * author：oliver
 * email：qiwei0727@163.com
 * date：on 2017/11/20
 */

public class Singleton {


    private Singleton() {
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
