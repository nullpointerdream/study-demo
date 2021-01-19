package com.mycrawler.mycrawler.spring.ioc.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-06 11:00
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        boolean person = beanDefinitionRegistry.containsBeanDefinition("person");
        boolean person2 = beanDefinitionRegistry.containsBeanDefinition("person2");
        if(person&&person2){
            BeanDefinition beanDefinition = new RootBeanDefinition(Black.class);
            beanDefinitionRegistry.registerBeanDefinition("black",beanDefinition);
        }
    }
}
