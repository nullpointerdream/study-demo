package com.mycrawler.mycrawler.spring.ioc;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import java.nio.charset.Charset;

public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {
    
    private String brand;
    
    private String color;
    
    private Integer maxSpeed;
    
    private String beanName;

    private BeanFactory beanFactory;
    
    public Car(){
        System.out.println("Car() 构造器调用");
    }
    
    @Override
    public void destroy() throws Exception {
        System.out.println("调用 DisposableBean destroy 方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用 InitializingBean afterPropertiesSet 方法");
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("调用 BeanNameAware setBeanName 方法");
        this.beanName = beanName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用 BeanFactoryAware setBeanFactory 方法");
        this.beanFactory = beanFactory;
    }
    
    public void introduce(){
        System.out.println("Car[brand : "+brand+",color:"+color+",maxSpeed:"+maxSpeed+"]");
    }
    
    @Override
    public String toString() {
        return "Car[brand : "+brand+",color:"+color+",maxSpeed:"+maxSpeed+"]";
    }
    
    public void myInit(){
        System.out.println("调用 init-method 指定的myInit 方法");
    }
    
    public void myDestroy(){
        System.out.println("调用 init-method 指定的myDestroy 方法");
    }
    
    //=====================================//
    
    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }
    
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBrand(String brand) {
        System.out.println("调用 setBrand 设置 属性");
        this.brand = brand;
    }

    public void setColor(String color) {
        System.out.println("调用 setColor 设置 属性");
        this.color = color;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        System.out.println("调用 setMaxSpeed 设置 属性");
        this.maxSpeed = maxSpeed;
    }


}