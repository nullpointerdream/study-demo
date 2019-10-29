package com.mycrawler.mycrawler.design.strategy.havior;

/**
 * @program: mycrawler
 * @description: 难听行为
 * @author: 陈家乐
 * @create: 2019-03-12 09:52
 **/

public class NanTingSingHavior implements SingHavior {
    @Override
    public void sing() {
        System.out.println("难听");
    }
}
