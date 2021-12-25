package com.study.IO.BIO.file;

import java.net.ServerSocket;
import java.net.Socket;

/**
  可以接收客户端任意类型文件，并保存在服务端磁盘；
 */
public class Server {
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(8080);
            while (true){
                Socket socket = ss.accept();
                //交给一个独立线程来处理与这个客户端的文件请求
                new ServerReadThread(socket).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
