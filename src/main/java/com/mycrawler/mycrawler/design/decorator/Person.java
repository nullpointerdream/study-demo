package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: mycrawler
 * @description: 人
 * @author: 陈家乐
 * @create: 2019-03-14 09:38
 **/

public abstract class Person {
    String desc;

    public String getDesc(){
        return desc;
    }

    public abstract double cost();
}
