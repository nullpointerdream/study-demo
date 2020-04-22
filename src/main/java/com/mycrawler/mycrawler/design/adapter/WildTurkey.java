package com.mycrawler.mycrawler.design.adapter;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-04-13 08:26
 **/
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("Im flying a short distance");
    }
}
