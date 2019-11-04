package com.mycrawler.mycrawler.thread.syn;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-01 16:27
 **/

public class Hello {
    public static void main(String[] args) {
        Hello hello=new Hello();
        for(int i=0;i<50_000;i++){
            hello.test();
        }
    }


    public void test() {
        int i = 8;
        while ((i -= 3) > 0);
        System.out.println(i);
        System.out.println("i = " + i);
    }
}
