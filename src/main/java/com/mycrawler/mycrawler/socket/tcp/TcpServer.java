package com.mycrawler.mycrawler.socket.tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: mycrawler
 * @description: tcp服务端
 * @author: 陈家乐
 * @create: 2019-02-27 14:36
 **/

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket accept=null;

        try {
            serverSocket = new ServerSocket();
            InetSocketAddress address = new InetSocketAddress("localhost", 9999);
            serverSocket.bind(address);
            while (true) {
                accept= serverSocket.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                String str = bufferedReader.readLine();
                System.out.println(str);
                String path=str.split(" ")[1];
                System.out.println("服务端收到" + path);


                File file =new File("/Users/chenjiale/Downloads"+path);
                String header=null;
                if(file.exists()){
                    header = " HTTP/1.1 200 OK\n Connection: close\nContent-Type: text/html\nContent-Length: "+file.length()+"\n";
                    BufferedOutputStream bufferedWriter = new BufferedOutputStream(accept.getOutputStream());
                    bufferedWriter.write("HTTP/1.1 404 Not Found".getBytes());
                    bufferedWriter.write("Content-Type: text/html; charset=UTF-8\n\n".getBytes());

                    BufferedReader bufferedInputStream=new BufferedReader(new FileReader(file));
                    String string=null;
                    while ((string=bufferedInputStream.readLine())!=null){
                        bufferedWriter.write(string.getBytes());
                    }
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }else {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
                    bufferedWriter.write("HTTP/1.1 404 Not Found");
                    bufferedWriter.write("Content-Type: text/html; charset=UTF-8\n\n");
                    bufferedWriter.write("<html>\n" + "<head>\n" + "    <title>first page</title>\n"
                            + "</head>\n" + "<body>\n" + "    <h1>404</h1>\n"
                            + "</body>\n" + "</html>\n");
                    bufferedWriter.flush();
                    bufferedWriter.close();

                }



               /* String next = str.toUpperCase();
                */
            }
        } catch (IOException e) {

        }

    }
}
