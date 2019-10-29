package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-03-14 09:47
 **/

public class Main {

    public static void main(String[] args) {
        Person person =new LiuDong();
        person=new Shirt(person);
        System.out.println(person.getDesc()+""  +person.cost());
        person=new RedHat(person);
        System.out.println(person.getDesc()+""  +person.cost());
    }
}
