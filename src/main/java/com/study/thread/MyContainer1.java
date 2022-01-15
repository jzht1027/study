package com.study.thread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyContainer1
 * @Description
 * 使用TeentrantLock
 * 生产者线程只叫醒消费者；
 * 消费者线程只叫醒生产者；
 * @Author
 * @Date 2022/1/14 17:55
 * @Version 1.0
 **/
public class MyContainer1<T> {
    private final LinkedList<T> lists = new LinkedList<>();
    private final int Max = 10; //最多10个元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition(); // 生产者
    private Condition consumer = lock.newCondition(); // 消费者

    public static void main(String[] args){
        MyContainer1<String> myContainer = new MyContainer1<String>();

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

    public void put(T t){
        try {
            lock.lock();
            while (lists.size()==Max) {
                System.out.println("生产等待");
                producer.await();
            }
            System.out.println("生产数据");
            lists.add(t);
            count++;
            consumer.signalAll();//通知消费者线程进行消费
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public T get(){
        T t = null;
        try {
            lock.lock();
            while (lists.size()==0) {
                System.out.println("消费等待");
                consumer.await();
            }
            System.out.println("消费数据");
            lists.add(t);
            count--;
            producer.signalAll();//通知生产者线程进行生产
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }
}
