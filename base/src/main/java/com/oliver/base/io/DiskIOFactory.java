package com.oliver.base.io;

/**
 * 功能：//创建disk存储的工厂
 * 开发：oliver
 * 时间：2017/10/10
 * 邮箱：qiwei0727@163.com
 */

public class DiskIOFactory implements IOFactory {
    @Override
    public IOHandler createIOHandler() {
        return new DiskIOHandler();
    }
}
