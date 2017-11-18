package com.oliver.baselibrary.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 功能：//例子（订阅者）
 * 作者：oliver
 * 日期：2017/6/15
 */
public class Demo extends Activity {


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserInfoEvent event) {
        //这里是接收消息
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //订阅
        EventBusManager.register(this);


        //发送消息
        EventBusManager.post(new UserInfoEvent("1", 1));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //取消订阅
        EventBusManager.unregister(this);
    }
}
