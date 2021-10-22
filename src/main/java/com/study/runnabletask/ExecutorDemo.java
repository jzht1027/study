package com.study.runnabletask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
//        for(int i=0;i<3;i++){
//            fixedThreadPool.execute(new MyRunable());
//            System.out.println("i:"+i);
//        }
        for (int i = 0; i < 5; i++) {
            Runnable runner = new MyRunable();
            fixedThreadPool.execute(new AutoRecoveryThread("MyRunable"+i, runner));
        }


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Run of Runnable");
//            }
//        }) {
//            public void run() {
//                System.out.println("Run of Thread");
//            }
//        }.start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Run of Runnable");
//            }
//        }) {
//            public void run() {
//                System.out.println("Run of Thread");
//                super.run();
//            }
//        }.start();

    }
}

