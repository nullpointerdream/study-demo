package com.mycrawler.mycrawler.design.obverse.java;

import com.mycrawler.mycrawler.design.obverse.MyOberver;
import com.mycrawler.mycrawler.design.obverse.Oberver;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @program: mycrawler
 * @description: 刘栋粉丝团
 * @author: 陈家乐
 * @create: 2019-03-13 13:22
 **/

public class LiuDongTeam extends Observable {

    private String singTime;

    private String zongYiTime;

    private String movieTime;


    private List<Oberver> list=new ArrayList<>();



    public void change(String singTime,String movieTime,String zongYiTime) {
        this.setMovieTime(singTime);
        this.setSingTime(movieTime);
        this.setZongYiTime(zongYiTime);
        setChanged();
        notifyObservers();

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
