package com.mycrawler.mycrawler.design.obverse.java;

import java.util.Observable;
import java.util.Observer;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-03-13 13:40
 **/

public class MingObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
            if(o instanceof  LiuDongTeam){
                LiuDongTeam liuDongTeam= (LiuDongTeam) o;
                System.out.println("明天刘栋 唱歌时间"+liuDongTeam.getSingTime()+" 电影时间"+liuDongTeam.getMovieTime()+"  综艺"+liuDongTeam.getZongYiTime());
            }
    }
}
