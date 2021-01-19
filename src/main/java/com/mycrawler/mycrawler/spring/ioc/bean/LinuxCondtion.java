package com.mycrawler.mycrawler.spring.ioc.bean;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-05 16:15
 **/
public class LinuxCondtion implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        BeanDefinitionRegistry registry = conditionContext.getRegistry();
        return !registry.containsBeanDefinition("person");
    }
}
