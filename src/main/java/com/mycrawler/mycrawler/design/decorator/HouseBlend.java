package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-03-31 09:21
 **/
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description="HouseBlend";
    }

    @Override
    public double cost() {
        return .89;
    }
}
