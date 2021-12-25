package com.study.IO.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThreadReader extends Thread{
    private Socket socket;

    public ServerThreadReader(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            //3、从socket管道中得到一个字节输入流；
            InputStream is = socket.getInputStream();
            //4、将字节输入流包装成一个缓冲字符输入流；
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null){
                System.out.println("服务端接收到："+msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
