package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-03-31 09:35
 **/
public class StartCoffee {
    public static void main(String[] args) {
        Beverage beverage =new Espresso();
        System.out.println(beverage.getDescription()+" $"+beverage.cost());

        Beverage beverage1=new Espresso();
        beverage1=new Milk(beverage1);
        beverage1=new Mocha(beverage1);
        beverage1=new Whip(beverage1);

        System.out.println(beverage1.getDescription()+" $"+beverage1.cost());
    }
}
