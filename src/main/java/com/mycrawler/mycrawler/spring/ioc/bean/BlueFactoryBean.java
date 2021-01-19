package com.mycrawler.mycrawler.spring.ioc.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-06 11:12
 **/
public class BlueFactoryBean implements FactoryBean<Blue> {
    @Override
    public Blue getObject() throws Exception {
        return new Blue();
    }

    @Override
    public Class<?> getObjectType() {
        return Blue.class;
    }
    //true 单利
    @Override
    public boolean isSingleton() {
        return true;
    }
}
