package com.study.IO.BIO.file;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * 实现客户端上传任意类型的文件数据给服务端保存起来
 */
public class client {
    public static void main(String[] srgs){
        try (
                InputStream is = new FileInputStream("C:\\Users\\Administrator\\Desktop\\视频资料\\前言.pdf");
        ){
            Socket socket = new Socket("127.0.0.1",8080);
            // 把字节输出流包装成数据输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            // 先发送上传文件的后缀给服务端
            dos.writeUTF(".pdf");
            // 把文件数据发送给服务端，进行接收
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0){
                dos.write(buffer,0,len);
            }
            dos.flush();
            //通知服务端数据发送完毕
            socket.shutdownOutput();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
