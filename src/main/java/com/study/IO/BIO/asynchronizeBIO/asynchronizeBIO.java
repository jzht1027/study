package com.study.IO.BIO.asynchronizeBIO;

import java.net.ServerSocket;
import java.net.Socket;

/**
    实现伪异步通信架构
 */
public class asynchronizeBIO {
    public static void main (String[] args){
        try {
            //1、注册端口
            ServerSocket ss = new ServerSocket(9999);
            //2、循环接收socket请求
            //创建线程池来处理客户端的socket请求
            HandlerSocketServerPool pool = new HandlerSocketServerPool(2,3,10);
            while (true){
                Socket socket = ss.accept();
                //将socket封装成一个任务对象，交给线程池
                Runnable target = new ServerRunnableTarget(socket);
                pool.execute(target);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
