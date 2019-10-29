package com.mycrawler.mycrawler.jvm;


import java.util.ArrayList;

/**
 * @program: mycrawler
 * @description: 堆溢出
 * @author: 陈家乐
 * -Xms1G -Xmx1G
 * @create: 2019-01-24 13:10
 **/

public class SimpleHeapOOm {
    public static void main(String[] args) {
        ArrayList<byte[]> list=new ArrayList<>();
        for(int i=0;i<1024;i++){
            list.add(new byte[1024*1024]);
        }

    }
}
