package com.oliver.base.io;

/**
 * 功能：//抽象产品
 * 开发：oliver
 * 时间：2017/10/10
 * 邮箱：qiwei0727@163.com
 */

public interface IOHandler {
    void save(String key, Object obj);

    void get(String key);
}
