package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: mycrawler
 * @description: Shirt
 * @author: 陈家乐
 * @create: 2019-03-14 09:42
 **/

public class Shirt extends ClothingDecorator {

    private Person person;

    public Shirt(Person person){
        this.person=person;
    }

    @Override
    public double cost() {
        return 15+person.cost();
    }

    @Override
    public String getDesc() {
        return person.desc+"shirt";
    }
}
