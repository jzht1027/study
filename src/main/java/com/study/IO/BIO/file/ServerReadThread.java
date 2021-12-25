package com.study.IO.BIO.file;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class ServerReadThread extends Thread{
    private Socket socket;

    public ServerReadThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            // 得到一个数据输入流，读取客户端发送过来的数据
            DataInputStream  dis = new DataInputStream(socket.getInputStream());
            //读取客户端发送过来的文件类型
            String suffix = dis.readUTF();
            // 定义一个字节输出管道负责把客户端发过来的文件数据写出去
            OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\"+
                    UUID.randomUUID().toString()+suffix);
            //从数据输入流中读取数据，写出到字节输出流
            byte[] buffer = new byte[1024];
            int len;
            while ( (len = dis.read(buffer)) >0){
                os.write(buffer,0,len);
            }
            os.close();
            System.out.println("服务端接收文件保存完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
