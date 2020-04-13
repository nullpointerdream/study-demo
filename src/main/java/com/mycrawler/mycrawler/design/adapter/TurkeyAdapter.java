package com.mycrawler.mycrawler.design.adapter;

/**
 * @program: study-demo
 * @description: 火鸡适配器
 * @author: chenjiale
 * @create: 2020-04-13 08:28
 **/
public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        //火鸡飞行短，需多次调用
        for(int i=0;i<5;i++) {
            turkey.fly();
        }
    }
}
