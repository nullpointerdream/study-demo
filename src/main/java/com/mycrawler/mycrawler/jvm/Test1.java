package com.mycrawler.mycrawler.jvm;

import java.nio.ByteBuffer;

public class Test1 {
    public static void main(String[] args) {
        ByteBuffer.allocateDirect(1024);
        System.out.println(Child1.str1);
    }
}
 
class Parent1 {
    public static String str1 = "hello jvm";
 
    static {
        System.out.println("Parent1 static block");
    }
}
 
class Child1 extends Parent1{
    public static String str = "child1";  // 1
 
    static {
        System.out.println("Child1 static block");
    }
}

