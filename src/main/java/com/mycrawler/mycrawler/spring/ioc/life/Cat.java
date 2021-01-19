package com.mycrawler.mycrawler.spring.ioc.life;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-07 19:54
 **/
@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat(){
        System.out.println("cat constucor");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("cat destory");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat afterPropertiesSet");
    }
}
