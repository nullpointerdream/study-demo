package com.mycrawler.mycrawler.doubt;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-21 11:01
 **/

public class Odd {
    public static void main(String[] args) {
        System.out.println("1是奇数吗？"+isOdd(1));
        System.out.println("2是奇数吗？"+isOdd(2));
        System.out.println("-1是奇数吗？"+isOdd2(-1));
        System.out.println(-1 % 2);
        System.out.println(-2 % 2);
    }

    //在任何负整数上调用该方法都回返回false，不管该整数是偶数还是奇数。
    public static boolean isOdd(int i){
        return i % 2 == 1;
    }

    public static boolean isOdd2(int i){
        return i % 2 != 0;
    }

    public static boolean isOdd3(int i){
        return (i & 1) != 0;
    }
}
