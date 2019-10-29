package com.mycrawler.mycrawler.design.strategy;

import com.mycrawler.mycrawler.design.strategy.havior.LookHavior;
import com.mycrawler.mycrawler.design.strategy.havior.SingHavior;

/**
 * @program: mycrawler
 * @description: 刘栋抽象类
 * @author: 陈家乐
 * @create: 2019-03-12 09:29
 **/

public class LiuDong {
     LookHavior lookHavior;
     SingHavior singHavior;

    public void performLook(){
        lookHavior.look();
    }


    public void performSing(){
        singHavior.sing();
    }


    public void setLookHavior(LookHavior lookHavior) {
        this.lookHavior = lookHavior;
    }

    public void setSingHavior(SingHavior singHavior) {
        this.singHavior = singHavior;
    }
}
