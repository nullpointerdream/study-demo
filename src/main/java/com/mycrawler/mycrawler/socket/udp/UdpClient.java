package com.mycrawler.mycrawler.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @program: mycrawler
 * @description: udp客户端
 * @author: 陈家乐
 * @create: 2019-02-27 13:42
 **/

public class UdpClient {

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        // 1.初始化DatagramSocket
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        // 2.创建用于发送消息的DatagramPacket
        InetSocketAddress address = new InetSocketAddress("localhost", 8888);
        String str=sc.next();
        DatagramPacket datagramPacket =new DatagramPacket(str.getBytes(),str.getBytes().length,address);
        // 3.向服务端发送消息
        try {
            datagramSocket.send(datagramPacket);
            // 4.创建用于接收消息的DatagramPacket
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            // 5.接收服务端消息
            datagramSocket.receive(receivePacket);
            System.out.println("服务端说:" + new String(receiveData,0,datagramPacket.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }


    }
}
