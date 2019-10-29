package com.mycrawler.mycrawler.design.proxy;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-05-23 09:55
 **/

public class Bird4 implements Flyable {
    private Flyable bird;
    public Bird4(Flyable flyable){
        bird=flyable;
    }

    @Override
    public void fly() {
        long start = System.currentTimeMillis();

        bird.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }

    public static void main(String[] args) {
        Bird bird = new Bird();
        Bird4 p1 = new Bird4(bird);
        Bird3 p2 = new Bird3(p1);

        p2.fly();
    }
}
