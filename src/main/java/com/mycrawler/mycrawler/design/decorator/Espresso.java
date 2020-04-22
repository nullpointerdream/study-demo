package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: study-demo
 * @description: 意大利浓咖啡
 * @author: chenjiale
 * @create: 2020-03-31 09:23
 **/
public class Espresso extends Beverage {
    public Espresso() {
        description="Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
