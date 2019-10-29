package com.mycrawler.mycrawler.design;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-05-15 14:40
 **/

public class Person {

    public void a(){
        System.out.println("parent a");
    }

    public void b(){
        System.out.println("parent b");
        a();
    }
}
