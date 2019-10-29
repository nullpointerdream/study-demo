package com.mycrawler.mycrawler.jvm;

import java.nio.ByteBuffer;

/**
 * @program: mycrawler
 * @description: 直接内存溢出
 * @author: 陈家乐
 * @create: 2019-01-24 13:17
 **/

public class DirectBufferOOM {
    public static void main(String[] args) {
        for(int i=0;i<1024;i++){
            ByteBuffer.allocateDirect(1024*1024);
            System.out.println(i);
        }
    }
}
