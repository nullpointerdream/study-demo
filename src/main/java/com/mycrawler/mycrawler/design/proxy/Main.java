package com.mycrawler.mycrawler.design.proxy;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-05-23 09:52
 **/

public class Main {
    public static void main(String[] args) {
        Bird2 bird2 =new Bird2();
        bird2.fly();

        Bird3 bird3=new Bird3(new Bird());
        bird3.fly();
    }
}
