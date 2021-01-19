package com.mycrawler.mycrawler.spring.ioc.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-07 20:01
 **/
//@Component
public class MyLifeBeanFactoryPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before-->"+bean+"---->"+beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after-->"+bean+"---->"+beanName);
        return null;
    }
}
