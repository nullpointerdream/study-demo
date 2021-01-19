package com.mycrawler.mycrawler.spring.ioc.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-05 11:54
 **/
public class Test {
    public static void main(String[] args) {
        //配置文件并启动容器
       /* ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person bean = (Person) factory.getBean("person2");
        System.out.println(bean);
*/
        ApplicationContext annotationContext=  new AnnotationConfigApplicationContext(MainConfig.class);
        Environment environment = annotationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        // Person bean = annotationContext.getBean(Person.class);
        //Person bean = (Person) annotationContext.getBean("person2");
        String[] beanNamesForType = annotationContext.getBeanNamesForType(Person.class);
        String[] beanDefinitionNames = annotationContext.getBeanDefinitionNames();
        for (String bean:beanDefinitionNames){
            System.out.println(bean);
        }

        Object blueFactoryBean = annotationContext.getBean("blueFactoryBean");
        Object blueFactoryBean2 = annotationContext.getBean("&blueFactoryBean");
        System.out.println(blueFactoryBean.getClass()+"---"+blueFactoryBean2.getClass());
        //System.out.println(bean);
    }

}
