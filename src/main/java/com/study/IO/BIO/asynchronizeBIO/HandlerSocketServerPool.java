package com.study.IO.BIO.asynchronizeBIO;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerSocketServerPool {
    //1、创建一个线程池的成员变量用于存储一个线程池对象
    private ExecutorService executorService;

    /**
        2、创建类对象时，初始化线程池对象

     public ThreadPoolExecutor(int corePoolSize,
        int maximumPoolSize,
        long keepAliveTime,
        TimeUnit unit,
        BlockingQueue<Runnable> workQueue)
     */
    public HandlerSocketServerPool (int corePoolSize,int maxThread , int queueSize){
        executorService = new ThreadPoolExecutor(corePoolSize,maxThread,120,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
    }


    /**
        3、提供一个方法：提交任务给线程池的任务队列来暂存，等待线程池来处理
     */
    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }
}
