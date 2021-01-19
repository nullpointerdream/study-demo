package com.mycrawler.mycrawler.spring.ioc.life;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-06 11:20
 **/
public class LiftTest {

    public static void main(String[] args) {
        ApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(LifeConfig.class);
        //configApplicationContext.
        Car bean = configApplicationContext.getBean(Car.class);
        Dog dog = configApplicationContext.getBean(Dog.class);
        System.out.println(bean);
        System.out.println(dog);
    }
}
