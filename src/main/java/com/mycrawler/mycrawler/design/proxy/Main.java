package com.mycrawler.mycrawler.design.proxy;

import java.lang.reflect.Proxy;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-05-23 09:52
 **/

public class Main {
    public static void main(String[] args) {
        Bird2 bird2 =new Bird2();
        bird2.fly();

        Bird3 bird3=new Bird3(new Bird());
        bird3.fly();


        //创建中介类实例
        DynamicProxy inter = new DynamicProxy(new Vendor());

        //获取代理类实例sell
        Sell sell = (Sell)(Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[] {Sell.class}, inter));

        //通过代理类对象调用代理类方法，实际上会转到invoke方法调用
        sell.sell();
        sell.ad();

    }
}
