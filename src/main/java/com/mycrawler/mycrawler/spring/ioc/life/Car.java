package com.mycrawler.mycrawler.spring.ioc.life;

/**
 * @program: study-demo
 * @description: qic
 * @author: chenjiale
 * @create: 2020-12-06 11:19
 **/
public class Car {
    public Car(){
        System.out.println("car  构造器");
    }

    public void init(){
        System.out.println("car init.....");
    }

    public void destory(){
        System.out.println("car destory.....");
    }
}
