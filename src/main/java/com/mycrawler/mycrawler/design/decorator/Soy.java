package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-03-31 09:26
 **/
public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Soy";
    }

    @Override
    public double cost() {
        return .30+beverage.cost();
    }
}
