package com.oliver.base.eventbus;

/**
 * 功能：//事件消息
 * 作者：oliver
 * 日期：2017/6/15
 */
public class UserInfoEvent {


    public String name;
    public int age;

    public UserInfoEvent(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
