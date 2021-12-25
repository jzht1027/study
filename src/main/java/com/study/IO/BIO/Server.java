package com.study.IO.BIO;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
    目标客户端反复发送消息，服务端可以反复接收消息
 */
public class Server {
    public static void main (String[] args){
        try {
            //1.定义一个ServerSocket对象进行服务端的端口注册
            ServerSocket ss = new ServerSocket(9999);
            //2.监听客户端的socket链接请求
            // 循环负责不断接收客户端的Socket链接
            while (true){
                Socket socket = ss.accept();
                //创建一个独立线程来处理客户端的socket请求
                new ServerThreadReader(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
