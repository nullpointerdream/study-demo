package com.mycrawler.mycrawler.design.obverse.java;

import com.mycrawler.mycrawler.design.obverse.Oberver;

import java.util.Observer;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-03-13 13:43
 **/

public class MainJava {
    public static void main(String[] args) {
        LiuDongTeam liuDongTeam =new LiuDongTeam();
        Observer observer1=new MingObserver();
        Observer observer2=new NowObserver();
        liuDongTeam.addObserver(observer1);
        liuDongTeam.addObserver(observer2);
        liuDongTeam.change("12点","13点","14点");
        liuDongTeam.change("17点","18点","19点");
        liuDongTeam.deleteObserver(observer2);
        liuDongTeam.change("17点","18点","19点");
    }


}
