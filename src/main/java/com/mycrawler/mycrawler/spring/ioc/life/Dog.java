package com.mycrawler.mycrawler.spring.ioc.life;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-07 19:59
 **/
@Component
public class Dog {
    public Dog(){
        System.out.println("dog Construct");
    }

    //疑问：beanPostProcessor 如果返回null，不执行init，PostConstruct基于beanPostProcessor实现？
    @PostConstruct
    public void init(){
        System.out.println("dog init..");
    }
}
