package com.lagnuage.yz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MyTomcatConnector {

//    private static  final Logger log = LoggerFactory.getLogger(MyTomcatConnector.class);

    private MyTomcatContainer myTomcatContainer;

    public void start(){
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            System.out.println("web service start");
            //这里已经设置了本机监听的接口，在tomcat中默认是8080
            serverSocket.bind(new InetSocketAddress(8888));

            while (true) {
                SocketChannel channel = serverSocketChannel.accept();
                HttpServer httpServer = new HttpServer();
                httpServer.setChannel(channel);
                new Thread(httpServer).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class HttpServer implements Runnable {

        private SocketChannel channel;

        public void setChannel(SocketChannel socketChannel) {
            this.channel = socketChannel;
        }

        @Override
        public void run() {
            if(channel != null) {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    InetSocketAddress address = (InetSocketAddress) channel.getRemoteAddress();
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    StringBuilder str = new StringBuilder();
                    while (byteBuffer.hasRemaining()) {
                        char c = (char) byteBuffer.get();
                        System.out.print(c);str.append(c);
                    }
                    String requestString = str.toString();
                    String[] lines = requestString.split("\n");
                    String resource = lines[0].substring(lines[0].indexOf('/'),lines[0].lastIndexOf('/') - 5);
//                    HttpServletRequest httpServletRequest = convert(lines);
//getServlet(resource).doservice();

                    System.out.println(Thread.currentThread().getName()+" : start to send back msg");
                    ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
                    String reply = "HTTP/1.1\n"; // 必须添加的响应头
                    reply += "Content-type:text;charset=utf-8/html\n\n"; // 必须添加的响应头
                    reply += "send back msg";
                    byteBuffer2.put(new String(reply).getBytes());
                    byteBuffer2.flip();
                    channel.write(byteBuffer2);
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
