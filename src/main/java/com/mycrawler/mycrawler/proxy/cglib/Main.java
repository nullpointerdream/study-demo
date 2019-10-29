package com.mycrawler.mycrawler.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-09-09 16:20
 **/

public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new MyMethodInterceptor());

        HelloConcrete hello = (HelloConcrete)enhancer.create();
        System.out.println(hello.sayHello("I love you!"));
        System.out.println(Integer.toBinaryString(110));
    }
}
