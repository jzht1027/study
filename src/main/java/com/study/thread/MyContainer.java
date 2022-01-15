package com.study.thread;

import java.sql.Time;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyContainer
 * @Description
 * 写一个固定容量同步容器，拥有put 和 get 方法，以及getCount()方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 使用 wait 和 notify/notifyAll 来实现；
 * @Author
 * @Date 2022/1/14 17:55
 * @Version 1.0
 **/
public class MyContainer<T> {
    private final LinkedList<T> lists = new LinkedList<>();
    private final int Max = 10; //最多10个元素
    private int count = 0;

    public static void main(String[] args){
        MyContainer<String> myContainer = new MyContainer<String>();
        //启动消费线程
        for (int i=0; i<50; i++){
            for(int j=0;j<5;j++){
                new Thread(()->{
                    System.out.println("调用消费线程:"+myContainer.get());
                },"c"+i).start();
            }
        }

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }

        //启动生产线程
        for(int i =0; i<2;i++ ){
            new Thread(()->{
                for(int j =0;j<25;j++ )
                    myContainer.put(Thread.currentThread().getName()+":"+j);
            },"p"+i).start();
        }
    }

    public synchronized void put(T t){
        while (lists.size()==Max){
            try{
                System.out.println("生产等待");
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("生产数据");
        lists.add(t);
        count++;
        this.notifyAll();
    }

    public synchronized T get(){
        T t = null;
        while (lists.size()==0){
            try{
                System.out.println("消费等待");
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("消费数据");
        lists.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }
}
