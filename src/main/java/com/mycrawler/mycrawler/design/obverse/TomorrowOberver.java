package com.mycrawler.mycrawler.design.obverse;

/**
 * @program: mycrawler
 * @description:刘栋明天行程
 * @author: 陈家乐
 * @create: 2019-03-13 13:29
 **/

public class TomorrowOberver implements Oberver{

    @Override
    public void update(String singTime, String movieTime, String zongYiTime) {
        System.out.println("明天刘栋 唱歌时间"+singTime+" 电影时间"+movieTime+"  综艺"+zongYiTime);
    }
}
