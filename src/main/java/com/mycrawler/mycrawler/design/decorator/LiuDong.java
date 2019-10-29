package com.mycrawler.mycrawler.design.decorator;

/**
 * @program: mycrawler
 * @description: 具体人
 * @author: 陈家乐
 * @create: 2019-03-14 09:46
 **/

public class LiuDong extends Person{

    public LiuDong() {
        desc = "Shopping List:";
    }

    @Override
    public double cost() {
        return 0;
    }
}
