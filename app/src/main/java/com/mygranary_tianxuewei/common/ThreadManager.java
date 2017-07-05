package com.mygranary_tianxuewei.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：田学伟 on 2017/7/5 18:56
 * QQ：93226539
 * 作用：线程池,联网下载的时候可以用
 *      ThreadManager.getInstance().getThread().execute(new Runnable())
 */

public class ThreadManager {
    private ThreadManager() {
    }

    private static ThreadManager manager = new ThreadManager();

    public static ThreadManager getInstance() {
        return manager;
    }

    private ExecutorService service = Executors.newCachedThreadPool();

    public ExecutorService getThread() {
        return service;
    }
}
