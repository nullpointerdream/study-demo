package com.mycrawler.mycrawler.jvm;

/**
 * @program: mycrawler
 * @description: 新生代配置参数
 * -Xms20M -Xmx20M -Xmn20M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @author: 陈家乐
 * @create: 2019-01-21 11:19
 **/

public class NewSizeDemo {

    public static void main(String[] args) {
        byte[] b=null;
        for(int i=0;i<10;i++){
            b=new byte[1*1024*1024];
        }
    }


}
