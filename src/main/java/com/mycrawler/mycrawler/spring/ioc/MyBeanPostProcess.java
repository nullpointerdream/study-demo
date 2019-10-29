package com.mycrawler.mycrawler.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcess implements BeanPostProcessor {

    public MyBeanPostProcess(){
        super();
                 System.out
                         .println("这是MyBeanPostProcess实现类构造器！！");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)throws BeansException {
        System.out.println("调用MyBeanPostProcess.postProcessBeforeInitialization 方法");
        if("car".equals(beanName)){
            Car car = (Car)bean;
            if(car.getColor() == null){
                car.setColor("黑色");
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)throws BeansException {
        System.out.println("调用 MyBeanPostProcess.postProcessAfterInitialization 方法");
        if("car".equals(beanName)){

        }else {

        }
        return bean;
    }

}