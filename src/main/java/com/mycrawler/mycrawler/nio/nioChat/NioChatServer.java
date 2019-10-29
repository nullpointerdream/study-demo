package com.mycrawler.mycrawler.nio.nioChat;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-07-22 09:42
 **/

public class NioChatServer {
    public static void main(String[] args) throws Exception {
        Map<String,SocketChannel> map = new HashMap<>();
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(1122));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            SocketChannel client=null;
            for (SelectionKey selectionKey : selectionKeys) {
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocket= (ServerSocketChannel) selectionKey.channel();
                    client= serverSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ);
                    map.put(UUID.randomUUID().toString(),client);
                    System.out.println(map.size());
                }else if(selectionKey.isReadable()){
                    client= (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer =ByteBuffer.allocate(512);
                    int read = client.read(byteBuffer);
                    if(read>0){
                        byteBuffer.flip();
                        StringBuilder sb =new StringBuilder();
                        while(byteBuffer.hasRemaining()) {
                            sb.append((char) byteBuffer.get());
                        }
                        String clientdId=null;
                        for (String s : map.keySet()) {
                            if(map.get(s).equals(client))
                                clientdId=s;
                        }
                        // 3.创建写数据缓存区对象
                        ByteBuffer writeBuffer =ByteBuffer.allocate(128);
                        writeBuffer.put(("["+clientdId+"]"+" say "+sb.toString()).getBytes());

                        for (Map.Entry<String, SocketChannel> stringSocketChannelEntry : map.entrySet()) {
                            writeBuffer.flip();
                            stringSocketChannelEntry.getValue().write(writeBuffer);
                        }



                    }
                }
            }
            selectionKeys.clear();


        }

    }
}
