package com.study.IO.BIO.endToEnd;

import org.mortbay.util.ajax.JSON;

import java.io.*;
import java.net.Socket;

public class ServerReadThread extends Thread{
    private Socket socket;
    public ServerReadThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = br.readLine()) !=null){
                //服务端接收到消息，转发给所有的在线客户端
                sendMsgToAllClient(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
            //客户端下线
            System.out.println("当前有人下线");
            Server.allSocketOnLine.remove(socket);
        }
    }

    private void sendMsgToAllClient(String msg) throws IOException {
        for(Socket socket : Server.allSocketOnLine){
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println(msg);
            ps.flush();
        }
    }
}
