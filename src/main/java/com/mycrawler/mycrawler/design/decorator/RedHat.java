package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: mycrawler
 * @description: redhat
 * @author: 陈家乐
 * @create: 2019-03-14 09:42
 **/

public class RedHat extends HatDecorator {

    private Person person;

    public RedHat(Person person){
        this.person=person;
    }

    @Override
    public double cost() {
        return 20+person.cost();
    }

    @Override
    public String getDesc() {
        return person.getDesc()+"redhat";
    }
}
