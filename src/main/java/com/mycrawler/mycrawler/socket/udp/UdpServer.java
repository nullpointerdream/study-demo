package com.mycrawler.mycrawler.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @program: mycrawler
 * @description: udp 服务端
 * @author: 陈家乐
 * @create: 2019-02-27 13:56
 **/

public class UdpServer {
    public static void main(String[] args) {
        DatagramSocket datagramSocket=null;
        try {
            // 1.初始化DatagramSocket 监听8888端口
            datagramSocket=new DatagramSocket(8888);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        // 4.创建用于接收消息的DatagramPacket
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            datagramSocket.receive(receivePacket);
            String receivestr=new String(receivePacket.getData(),0,receivePacket.getLength());
            System.out.println("服务端接受数据"+receivestr);
            String s = receivestr.toUpperCase();
            datagramSocket.send(new DatagramPacket(s.getBytes(), s.getBytes().length,receivePacket.getSocketAddress()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
