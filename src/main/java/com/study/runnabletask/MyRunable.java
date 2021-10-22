package com.study.runnabletask;


public class MyRunable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"  "+Thread.currentThread().getId());
    }
}