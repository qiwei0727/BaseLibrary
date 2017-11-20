package com.oliver.base.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能：//线程池
 * 作者：oliver
 * 日期：2017/6/30
 */
public class ThreadManager {
    private static ThreadPool mNormalPool = new ThreadPool(3, 3, 5 * 1000);

    /**
     * 获取一个普通的线程池
     *
     * @return
     */
    public static ThreadPool getNormalPool() {
        return mNormalPool;
    }

    private static ThreadPool mThreadPool= null;

    public static ThreadPool getInstance() {
        if (mThreadPool == null) {
            synchronized (ThreadManager.class) {
                if (mThreadPool == null) {
                    //10个核心线程，休息1s
                    mThreadPool = new ThreadPool(10, 10, 1L);
                }
            }
        }
        return mThreadPool;
    }

    /**
     * 线程池
     */
    public static class ThreadPool {

        private ThreadPoolExecutor executor;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         * 执行线程
         *
         * @param r 我们的异步操作
         */
        public void execute(Runnable r) {
            if (executor == null) {
                executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
            }
            executor.execute(r);
        }
        /**
         * 移除任务的方法
         *
         * @param task 需要移除的任务对象
         */
        public void remove(Runnable task) {
            if (executor != null && !executor.isShutdown()) {
                executor.getQueue().remove(task);
            }
        }
    }
}
