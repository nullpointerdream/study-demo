package com.mycrawler.mycrawler.design.strategy.havior;

/**
 * @program: mycrawler
 * @description: 帅哥
 * @author: 陈家乐
 * @create: 2019-03-12 09:41
 **/

public class HandSomeHavior implements LookHavior {
    @Override
    public void look() {
        System.out.println("帅哥");
    }
}
