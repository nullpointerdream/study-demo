package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-03-31 09:22
 **/
public class DarkRoast extends Beverage {
    public DarkRoast() {
        description="DarkRoast";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
