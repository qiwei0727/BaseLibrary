package com.oliver.baselibrary.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * 功能：//EventBus操作类
 * 作者：oliver
 * 日期：2017/6/15
 */
public class EventBusManager {

    /**
     * 订阅
     *
     * @param object
     */
    public static void register(Object object) {
        if (!EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().register(object);
        }
    }

    /**
     * 取消订阅
     *
     * @param object
     */
    public static void unregister(Object object) {
        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
        }
    }

    /**
     * 发送消息
     *
     * @param obj
     */
    public static void post(Object obj) {
        EventBus.getDefault().post(obj);
    }
}
