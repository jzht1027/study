package com.study.ThreadSharedData;

public class ThreadShareData {
    public static void main(String[] args){
        SharedData sharedData = new SharedData();

//        for (int i =0; i<6;i++){
//            new Thread(new MyRunnable2(sharedData)).run();
//            new Thread(new MyRunnable1(sharedData)).run();
//        }

        for (int i=1;i<=20;i++){

            int finalI = i;
            new Thread(()->{
                for(int j =0;j<100000;j++){
                    if (finalI <=10){
                        sharedData.increment();
                    }else {
                        sharedData.decrement();
                    }
                }
            }).start();
        }
    }
}

//增加的线程，需要传入一个共享数据
class MyRunnable1 implements Runnable{
    private SharedData data;
    public MyRunnable1(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            data.increment();
        }
    }
}

//减少的线程，需要传入一个共享数据
class MyRunnable2 implements Runnable{

    private SharedData data;
    public MyRunnable2(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            data.decrement();
        }
    }
}


//封装共享数据和操作共享数据方法的类
class SharedData {
    private int share =0;

    public void increment(){
        share++;
        System.out.println(Thread.currentThread().getName() + "  inc: "+share);
    }

    public synchronized void decrement(){
        share--;
        System.out.println(Thread.currentThread().getName() + "  dec: "+share);
    }
}