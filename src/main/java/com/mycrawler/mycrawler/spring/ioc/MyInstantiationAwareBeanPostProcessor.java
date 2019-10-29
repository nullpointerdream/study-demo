package com.mycrawler.mycrawler.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    
    public Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException {
        if("car".equals(beanName)){
            System.out.println("MyInstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation 方法");
        }
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }
    
    public boolean postProcessAfterInstantiation(Object bean, String beanName)throws BeansException {
        if("car".equals(beanName)){
            System.out.println("MyInstantiationAwareBeanPostProcessor.postProcessAfterInstantiation 方法");
        }
        return super.postProcessAfterInstantiation(bean, beanName);
    }
    
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        if("car".equals(beanName)){
            System.out.println("MyInstantiationAwareBeanPostProcessor.postProcessPropertyValues 方法");
        }
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}