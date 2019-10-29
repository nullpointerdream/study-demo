package com.mycrawler.mycrawler.design.strategy.havior;

/**
 * @program: mycrawler
 * @description:  一般好看
 * @author: 陈家乐
 * @create: 2019-03-12 09:53
 **/

public class YiBanLookHavior implements LookHavior{
    @Override
    public void look() {
        System.out.println("一般");
    }
}
