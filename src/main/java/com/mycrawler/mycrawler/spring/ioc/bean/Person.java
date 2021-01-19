package com.mycrawler.mycrawler.spring.ioc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-05 11:53
 **/
@Data
@AllArgsConstructor
public class Person {
    private String name;
    private Integer age;
    public Person(){

    }
}
