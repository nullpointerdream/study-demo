package com.mycrawler.mycrawler.suanfa;

import java.util.HashMap;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-10-09 20:13
 **/
public class IPUtils {
    public static int ipToInt(String ip){
        String[] split = ip.split("\\.");
        int rel = 0;
        for(int i=0;i<split.length;i++){
            int num = Integer.parseInt(split[i]);
            int i1 = num << (i*8);
            rel |= i1;
        }
        return rel;

    }

    public static String intToIP(int ipInt){
        String[] ipString = new String[4];
        for (int i = 0; i < 4; i++) {
            int pos = i * 8;
            int and = ipInt & (255 << pos);
            ipString[i] = String.valueOf(and >>> pos);
        }
        return String.join(".", ipString);

    }

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        System.out.println(IPUtils.ipToInt("192.168.1.1"));
        System.out.println(IPUtils.intToIP(IPUtils.ipToInt("192.168.1.1")));
    }
}
