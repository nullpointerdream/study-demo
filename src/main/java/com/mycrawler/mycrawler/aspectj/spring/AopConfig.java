package com.mycrawler.mycrawler.aspectj.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-29 09:37
 **/
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    public LosApesct losApesct(){
        return new LosApesct();
    }

    @Bean
    public MathCalcuator mathCalcuator(){
        return new MathCalcuator();
    }


}
