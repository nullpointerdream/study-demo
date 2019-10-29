package com.mycrawler.mycrawler.jvm;

/**
 * @program: mycrawler
 * @description: 初创对象在eden区
 * @author: 陈家乐
 * -Xmx64M -Xms64M -XX:+PrintGCDetails
 * @create: 2019-01-22 11:34
 **/

public class AllocEden {
    public static final Integer _1k=1024;

    public static void main(String[] args) {

        for(int i=0;i<_1k;i++){
            byte[] bytes=new byte[_1k];
        }
    }
}
