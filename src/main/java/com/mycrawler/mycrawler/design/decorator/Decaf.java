package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-03-31 09:23
 **/
public class Decaf extends Beverage {
    public Decaf() {
        description="Decaf";
    }

    @Override
    public double cost() {
        return .99;
    }
}
