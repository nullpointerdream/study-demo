package com.mycrawler.mycrawler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-03-05 18:25
 **/

public class TestStatic {
    private int i=Mi.getInt();
    public static void main(String[] args) {
        TestStatic testStatic =new TestStatic();
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("");
    }
}
class Mi{
    static {
        System.out.println("static");
    }
    public static int getInt(){
        return 1;
    }
}