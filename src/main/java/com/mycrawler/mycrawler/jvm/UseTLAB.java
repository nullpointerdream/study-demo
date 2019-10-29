package com.mycrawler.mycrawler.jvm;

/**
 * @program: mycrawler
 * @description: 线程本地分配缓存
 * @author: 陈家乐
 * @create: 2019-01-22 14:06
 * -server -XX:+UseTLAB -Xcomp -XX:-DoEscapeAnalysis -XX:-BackgroundCompilation
 **/

public class UseTLAB {

    public static void alloc(){
        byte[] bytes= new byte[2];
        bytes[0]=2;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for(int i=0;i<10000000;i++){
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }
}
