package com.mycrawler.mycrawler.proxy;

import java.lang.reflect.Proxy;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-09-05 13:05
 **/

public class Main {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IHello o = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), new Class[]{IHello.class}, new MyInvocationHandler(new HelloImpl()));
        o.sayHello();
        o.hashCode();
    }
}
