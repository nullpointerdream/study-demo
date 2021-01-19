package com.mycrawler.mycrawler.aspectj.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-29 09:38
 **/
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AopConfig.class);
        MathCalcuator bean = applicationContext.getBean(MathCalcuator.class);
        int add = bean.add(1, 2);
    }
}
