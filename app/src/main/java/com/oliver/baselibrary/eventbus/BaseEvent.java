package com.oliver.baselibrary.eventbus;

/**
 * 功能：//消息的基类
 * 作者：oliver
 * 日期：2017/6/15
 */
public class BaseEvent<T> {

    public final String message;
    public T t;

    public BaseEvent(String message, T t) {
        this.t = t;
        this.message = message;
    }
}