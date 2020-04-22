package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: study-demo
 * @description: 饮料
 * @author: chenjiale
 * @create: 2020-03-31 09:13
 **/
public abstract class Beverage {
    String description="Unknown Beverage";

    public  String getDescription(){
        return description;
    }

    public abstract double cost();
}
