package com.study.IO.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @ClassName ClientChannel
 * @Description
 * @Author
 * @Date 2021/12/27 18:07
 * @Version 1.0
 **/
public class ClientChannel {
    private static SocketChannel sChannel;
    private static Selector selector;
    private int port = 9999;

    public ClientChannel(){
        try{
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",port));
            sChannel.configureBlocking(false);
            selector = Selector.open();
            sChannel.register(selector, SelectionKey.OP_READ);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ClientChannel clientChannel = new ClientChannel();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ReadInfo();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String ms = scanner.nextLine();
                sChannel.write(ByteBuffer.wrap(("客户端："+ms).getBytes()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void ReadInfo() throws Exception {
        SelectionKey selectionKey = null;
        SocketChannel sChannel = null;
        try {
            while(selector.select() > 0){
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()){
                    selectionKey = it.next();
                    if(selectionKey.isReadable()){
                        sChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        sChannel.read(buffer);
                        System.out.println(new String(buffer.array()).trim());
                    }
                    it.remove();
                }
            }
        }catch (Exception e){
            try {
                System.out.println("服务端下线");
                //取消注册
                selectionKey.cancel();
                //关闭通道
                sChannel.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
