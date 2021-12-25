package com.study.IO.BIO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main (String[] args){
        try {
            //1.创建Socket 对象请求服务端请求
            Socket socket = new Socket("127.0.0.1",9999);
            //2.从socket中获取一个字节输出流
            OutputStream os = socket.getOutputStream();
            //3.把字节输入流包装成一个打印流
            PrintStream ps = new PrintStream(os);
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()){
                ps.println("客户端："+sc.nextLine());
                ps.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2。
    }
}
