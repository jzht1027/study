package com.study.IO.BIO.endToEnd;

import com.study.IO.BIO.ServerThreadReader;
import org.apache.avro.generic.GenericData;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * BIO模式下的端口转发思想
 *  服务端：
 *      注册端口；
 *      接收客户端的socket链接，交给一个独立的线程处理；
 *      把当前链接的客户端socket存入到一个所谓的在线socket集合中保存；
 *      接收客户端的消息，然偶推送给当前所有在线的socket接收；
 */
public class Server {
    //定义一个静态集合
    public static List<Socket> allSocketOnLine = new ArrayList<>();

    public static void main (String[] args){
        try {
            //1.定义一个ServerSocket对象进行服务端的端口注册
            ServerSocket ss = new ServerSocket(9999);
            //2.监听客户端的socket链接请求
            // 循环负责不断接收客户端的Socket链接
            while (true){
                Socket socket = ss.accept();
                //把登录的客户端socket存入到一个在线集合中去
                allSocketOnLine.add(socket);
                //为当前登录成功的socket分配一个独立的线程处理；
                new ServerReadThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
