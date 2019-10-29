package com.mycrawler.mycrawler.design.proxy;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-05-23 09:51
 **/

public class Bird3 implements Flyable{
    private Flyable bird;
    public Bird3(Flyable flyable){
        bird=flyable;
    }
    @Override
    public void fly() {
        System.out.println("开始");
        bird.fly();
        System.out.println("结束");
    }
}
