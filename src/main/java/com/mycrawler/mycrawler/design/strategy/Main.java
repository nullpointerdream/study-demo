package com.mycrawler.mycrawler.design.strategy;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-03-12 09:46
 **/

public class Main {
    public static void main(String[] args) {
        LiuDong liuDong=new HandSomeLiuDong();
        liuDong.performLook();
        liuDong.performSing();

        LiuDong chenjiale=new YibanChenJiaLE();
        chenjiale.performLook();
        chenjiale.performSing();
    }
}
