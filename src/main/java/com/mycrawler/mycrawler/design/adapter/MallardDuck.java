package com.mycrawler.mycrawler.design.adapter;

/**
 * @program: study-demo
 * @description: 绿头鸭
 * @author: chenjiale
 * @create: 2020-04-13 08:24
 **/
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("i'm flying");
    }
}
