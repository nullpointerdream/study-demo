package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-03-31 09:25
 **/
public class Milk extends CondimentDecorator {
    Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Milk";
    }

    @Override
    public double cost() {
        return .40+beverage.cost();
    }
}
