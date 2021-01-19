package com.mycrawler.mycrawler.aspectj.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-29 09:26
 **/
@Aspect
public class LosApesct {

    @Pointcut("execution(public int com.mycrawler.mycrawler.aspectj.spring.MathCalcuator.*(..))")
    private void cutPoint(){

    }

    @Before(value = "cutPoint()")
    private void before(JoinPoint joinPoint){
        System.out.println("before+.."+joinPoint.getArgs());
    }

    @After(value = "cutPoint()")
    private void after(){
        System.out.println("after");
    }

    @AfterReturning(value = "cutPoint()",returning = "re" )
    private void returnn(Object re){
        System.out.println("after--"+re);
    }
    @AfterThrowing(value = "cutPoint()")
    private void exception(){

    }


}
