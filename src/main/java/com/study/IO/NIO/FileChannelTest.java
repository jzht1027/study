package com.study.IO.NIO;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName ChannelTest
 * @Description
 * @Author
 * @Date 2021/12/27 15:28
 * @Version 1.0
 **/
public class FileChannelTest {

    @Test
    public void transferTo(){
        try {
            //源通道
            FileInputStream fis = new FileInputStream("data01.txt");
            FileChannel isChannel = fis.getChannel();
            //目的通道
            FileOutputStream fos = new FileOutputStream("data03.txt");
            FileChannel osChannel = fos.getChannel();

            isChannel.transferTo(isChannel.position() , isChannel.size(),osChannel);
            isChannel.close();
            osChannel.close();
            System.out.println("文件复制完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferFrom(){
        try {
            //源通道
            FileInputStream fis = new FileInputStream("data01.txt");
            FileChannel isChannel = fis.getChannel();
            //目的通道
            FileOutputStream fos = new FileOutputStream("data03.txt");
            FileChannel osChannel = fos.getChannel();

            osChannel.transferFrom(isChannel,isChannel.position() , isChannel.size());
            isChannel.close();
            osChannel.close();
            System.out.println("文件复制完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        try {
            //得到字节输入流
            FileInputStream fis = new FileInputStream("data01.txt");
            FileChannel isChannel = fis.getChannel();
            //字节输出流
            FileOutputStream fos = new FileOutputStream("data02.txt");
            FileChannel osChannel = fos.getChannel();

            //定义多个缓冲区，数据分散
            ByteBuffer buffer1 = ByteBuffer.allocate(4);
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);
            ByteBuffer[] buffers = {buffer1,buffer2};
            // 从通道中读取数据分散到各个缓冲区
            isChannel.read(buffers);
            //从每个缓冲区查询书否取到数据了
            for(ByteBuffer buffer : buffers){
                buffer.flip();
                System.out.println(new String(buffer.array(),0,buffer.remaining()));
            }
            //聚集写入到通道
            osChannel.write(buffers);
            isChannel.close();
            osChannel.close();
            System.out.println("文件复制完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void copy(){
        try {
            //1.源文件
            File srcfile = new File("C:\\Users\\jiazhitao\\Desktop\\2021年季度员工述职报告.pptx");
            File desfile = new File("C:\\Users\\jiazhitao\\Desktop\\2021年季度员工述职报告(1).pptx");
            //得到字节输入流
            FileInputStream fis = new FileInputStream(srcfile);
            //字节输出流
            FileOutputStream fos = new FileOutputStream(desfile);
            FileChannel srcChannel = fis.getChannel();
            FileChannel desChannel = fos.getChannel();

            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true){
                //必须清空缓冲区在写入数据到缓冲区
                buffer.clear();
                //开始读取一次数据
                int flag = srcChannel.read(buffer);
                if(flag == -1){
                    break;
                }
                //已经读取了数据，将缓冲区模式切换成可读moshi
                buffer.flip();
                desChannel.write(buffer);
            }

            srcChannel.close();
            desChannel.close();
            System.out.println("复制完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read(){
        try {
            //1.定一个文件字节输入流与源文件
            FileInputStream fis = new FileInputStream("data01.txt");
            // 2.需要得到文件字节输入流的文件通道
            FileChannel channel = fis.getChannel();
            // 3.定义缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //4.读取数据到缓冲区
            channel.read(buffer);
            buffer.flip();
            //5.读取缓冲区的数据并输出
            String rs = new String(buffer.array(),0,buffer.remaining());
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void write (){
        try {
            //1.字节输出流通向目标文件
            FileOutputStream fos = new FileOutputStream("data01.txt");
            //2.得到字节输出流对应的通道；
            FileChannel channel = fos.getChannel();
            ByteBuffer bufer = ByteBuffer.allocate(1024);
            bufer.put("你好，程序员！".getBytes());
            //3.把缓冲区切换成写出模式
            bufer.flip();
            channel.write(bufer);
            channel.close();
            System.out.println("写数据到文件中");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
