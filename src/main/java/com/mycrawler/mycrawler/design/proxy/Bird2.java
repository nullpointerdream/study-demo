package com.mycrawler.mycrawler.design.proxy;

public class Bird2 extends Bird {

    @Override
    public void fly() {
        long start = System.currentTimeMillis();
        
        super.fly();
        
        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}
