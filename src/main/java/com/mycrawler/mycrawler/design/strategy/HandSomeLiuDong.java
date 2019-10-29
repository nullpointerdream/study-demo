package com.mycrawler.mycrawler.design.strategy;


import com.mycrawler.mycrawler.design.strategy.havior.HandSomeHavior;
import com.mycrawler.mycrawler.design.strategy.havior.HaoTingSingHavior;

/**
 * @program: mycrawler
 * @description: 帅哥刘栋
 * @author: 陈家乐
 * @create: 2019-03-12 09:40
 **/

public class HandSomeLiuDong extends LiuDong {
    public HandSomeLiuDong(){
        lookHavior=new HandSomeHavior();
        singHavior=new HaoTingSingHavior();
    }
}
