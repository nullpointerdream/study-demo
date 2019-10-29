package com.mycrawler.mycrawler.design.strategy;

import com.mycrawler.mycrawler.design.strategy.havior.HandSomeHavior;
import com.mycrawler.mycrawler.design.strategy.havior.HaoTingSingHavior;
import com.mycrawler.mycrawler.design.strategy.havior.NanTingSingHavior;
import com.mycrawler.mycrawler.design.strategy.havior.YiBanLookHavior;

/**
 * @program: mycrawler
 * @description: 一般陈家乐
 * @author: 陈家乐
 * @create: 2019-03-12 09:54
 **/

public class YibanChenJiaLE extends LiuDong{
    public YibanChenJiaLE(){
        lookHavior=new YiBanLookHavior();
        singHavior=new NanTingSingHavior();
    }
}
