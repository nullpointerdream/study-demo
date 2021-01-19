package com.mycrawler.mycrawler.spring.ioc.life;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-06 11:19
 **/
@Configuration
@ComponentScan(value = "com.mycrawler.mycrawler.spring.ioc.life")
public class LifeConfig {

    //@Scope(value = "prototype")
    @Bean(initMethod = "init",destroyMethod = "destory")
    public Car car(){
        return new Car();
    }
}
