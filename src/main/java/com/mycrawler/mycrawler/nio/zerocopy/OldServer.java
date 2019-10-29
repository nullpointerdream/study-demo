package com.mycrawler.mycrawler.nio.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-07-24 10:04
 **/

public class OldServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket =new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();
            long start=System.currentTimeMillis();
            DataInputStream dataInputStream =new DataInputStream(socket.getInputStream());
            try {
                byte[] bytes=new byte[4096];
                int size=0;
                int totle=0;
                while ((size=dataInputStream.read(bytes,0,bytes.length))>=0){
                    totle+=size;
                }
                dataInputStream.close();
                System.out.println("总字节："+totle+"   耗时"+(System.currentTimeMillis()-start)+"");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
