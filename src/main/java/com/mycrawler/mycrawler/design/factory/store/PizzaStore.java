package com.mycrawler.mycrawler.design.factory.store;

import com.mycrawler.mycrawler.design.factory.pizza.Pizza;

/**
 * @program: study-demo
 * @description: 披萨店
 * @author: chenjiale
 * @create: 2020-04-02 09:13
 **/
public abstract class PizzaStore {
    public Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    public abstract Pizza createPizza(String type);
}
