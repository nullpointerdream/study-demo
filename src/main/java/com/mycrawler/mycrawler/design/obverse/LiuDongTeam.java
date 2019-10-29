package com.mycrawler.mycrawler.design.obverse;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mycrawler
 * @description: 刘栋粉丝团
 * @author: 陈家乐
 * @create: 2019-03-13 13:22
 **/

public class LiuDongTeam implements MyOberver {

    private String singTime;

    private String zongYiTime;

    private String movieTime;


    private List<Oberver> list=new ArrayList<>();

    @Override
    public void add(Oberver oberver) {
        list.add(oberver);
    }

    @Override
    public void remove(Oberver oberver) {
        list.remove(oberver);
    }

    @Override
    public void callObvers() {
         for(Oberver oberver :list){
             oberver.update(singTime,movieTime,zongYiTime);
         }
    }

    public void change(String singTime,String movieTime,String zongYiTime) {
        this.setMovieTime(singTime);
        this.setSingTime(movieTime);
        this.setZongYiTime(zongYiTime);
        callObvers();
    }

    public String getSingTime() {
        return singTime;
    }

    public void setSingTime(String singTime) {
        this.singTime = singTime;
    }

    public String getZongYiTime() {
        return zongYiTime;
    }

    public void setZongYiTime(String zongYiTime) {
        this.zongYiTime = zongYiTime;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }
}
