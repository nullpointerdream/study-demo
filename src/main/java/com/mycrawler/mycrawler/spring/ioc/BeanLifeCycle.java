package com.mycrawler.mycrawler.spring.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BeanLifeCycle {
    private static void beanLifeCycleInFactory(){
        
        //配置文件并启动容器
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory)factory);
        reader.loadBeanDefinitions(resource);
        
        //向容器中注册 MyBeanPostProcess 后处理器
        ((DefaultListableBeanFactory)factory).addBeanPostProcessor(new MyBeanPostProcess());

        //向容器中注册 MyInstantiationAwareBeanPostProcessor 后处理器
        ((DefaultListableBeanFactory)factory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        
        //从容器中获取bean 将触发容器中实例化 bean 这将引发Bean 生命周期方法的调用
        Car car1 = factory.getBean(Car.class);

        car1.introduce();

        //第二次获取bean  直接从缓存中读取
        Car car2 = factory.getBean(Car.class);

        //查看 car1 和 car2是否是同一个引用
        System.out.println(car1 == car2);

        //关闭容器
        ((DefaultListableBeanFactory)factory).destroySingletons();

        //((ClassPathXmlApplicationContext)ctx).destroy();
    }

    private static void beanLifeCycleInFactory2(){

        //配置文件并启动容器
        ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");

        //从容器中获取bean 将触发容器中实例化 bean 这将引发Bean 生命周期方法的调用
      //  Car car1 = factory.getBean(Car.class);

      //  car1.introduce();

        //第二次获取bean  直接从缓存中读取
      //  Car car2 = factory.getBean(Car.class);

        //查看 car1 和 car2是否是同一个引用
       // System.out.println(car1 == car2);

        //关闭容器
      //  ((DefaultListableBeanFactory)factory).destroySingletons();

        //((ClassPathXmlApplicationContext)ctx).destroy();
    }
    
    public static void main(String[] args) {
        BeanLifeCycle.beanLifeCycleInFactory2();
    }
}