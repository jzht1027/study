package com.study.IO.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @ClassName ServerChannel
 * @Description NIO 非阻塞通信下的案例
 * @Author
 * @Date 2021/12/27 17:27
 * @Version 1.0
 **/
public class ServerChannel {
    private static Selector selector;
    private static ServerSocketChannel ssChannel;
    private int port = 9999;

    public ServerChannel(){
        try{
            //获得通道
            ssChannel = ServerSocketChannel.open();
            //切换为非阻塞
            ssChannel.configureBlocking(false);
            //绑定端口
            ssChannel.bind(new InetSocketAddress(port));
            //获取选择器
            selector = Selector.open();
            //将通道注册到选择器，并且指定监听事件
            ssChannel.register(selector,SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try  {
            ServerChannel sChannel = new ServerChannel();

            listening();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void listening() throws Exception {
        //轮询获取选择器上已经准备就绪的事件
        while (selector.select() > 0){
            //获取选择器中的所有注册的通道中已经就绪的事件
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
                //提取当前事件
                SelectionKey selectionKey = it.next();
                if (selectionKey.isAcceptable()){//接收事件
                    System.out.println("客户端发起连接请求");
                    //直接获取当前接入的客户端通道
                    SocketChannel socketChannel = ssChannel.accept();
                    //切换到为非阻塞模式
                    socketChannel.configureBlocking(false);
                    //将通道注册时选择器上
                    socketChannel.register(selector , SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()){
                    read(selectionKey);
                }
                //处理完毕需要移除当前事件
                it.remove();
            }
        }
    }

    private static void read(SelectionKey selectionKey) {
        SocketChannel sChannel =null;
        try{
            //获取当前选择器上的读就绪事件
            sChannel = (SocketChannel) selectionKey.channel();
            // 读取数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len = sChannel.read(buffer);
            if (len  >0){
                buffer.flip();
                String msg = new String(buffer.array(),0,len);
                System.out.println(msg);
                //转发给其他所有的通道
                sendToAllClient(msg,sChannel);
            }

        }catch (Exception e){
            try {
                System.out.println("客户端下线");
                //取消注册
                selectionKey.cancel();
                //关闭通道
                sChannel.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

    private static void sendToAllClient(String msg, SocketChannel sChannel) throws Exception{
        System.out.println("服务端转发消息"+Thread.currentThread().getName());
        for(SelectionKey selectionKey : selector.keys()){
            Channel channel = selectionKey.channel();
            if(channel instanceof SocketChannel && (channel != sChannel)){
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                ((SocketChannel) channel).write(buffer);
            }
        }
    }

}
