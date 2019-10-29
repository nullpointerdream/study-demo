package com.mycrawler.mycrawler.socket.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: mycrawler
 * @description: tcp客户端
 * @author: 陈家乐
 * @create: 2019-02-27 14:42
 **/

public class TcpClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",9999);
            Scanner sc =new Scanner(System.in);
            String next = sc.next();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(next);
            bufferedWriter.newLine();//必须加换行符才能收到信息
            bufferedWriter.flush();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("回复"+bufferedReader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
