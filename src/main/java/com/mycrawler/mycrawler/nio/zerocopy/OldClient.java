package com.mycrawler.mycrawler.nio.zerocopy;

import com.mycrawler.mycrawler.thread.readwrite.Data;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-07-24 10:04
 **/

public class OldClient {
    public static void main(String[] args) throws IOException {
        Socket socket =new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",8899));
        String fileName="/Users/chenjiale/java_error_in_idea.hprof";
        DataOutputStream outputStream =new DataOutputStream(socket.getOutputStream());
        InputStream inputStream =new FileInputStream(fileName);
        byte[] bytes =new byte[4096];
        int totle=0;
        int size=0;
        long start=System.currentTimeMillis();
        while ((size=inputStream.read(bytes,0,bytes.length))>=0){
            totle+=size;
            outputStream.write(bytes);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        System.out.println("总字节："+totle+"   耗时"+(System.currentTimeMillis()-start)+"");
    }
}
