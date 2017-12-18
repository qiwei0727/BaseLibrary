package com.oliver.base.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * description：//json解析工具
 * author：Oliver
 * email：qiwei0727@163.com
 * date：on 2017/12/18
 */

public class GsonUtil {
    private Gson mGson;

    private GsonUtil() {
        mGson = new Gson();
    }

    private static class SingletonHolder {
        private static final GsonUtil INSTANCE = new GsonUtil();
    }

    public static GsonUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Gson getGson() {
        return mGson;
    }

    public static String toJson(Object src) {
        return getInstance().getGson().toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return getInstance().getGson().fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return getInstance().getGson().fromJson(json, typeOfT);
    }
}

