package com.mycrawler.mycrawler.design.obverse;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-03-13 13:33
 **/

public class Main {
    public static void main(String[] args) {
        LiuDongTeam liuDongTeam=new LiuDongTeam();
        Oberver oberver1 =new TodayOberver();
        Oberver oberver2 =new TomorrowOberver();
        liuDongTeam.add(oberver1);
        liuDongTeam.add(oberver2);
        liuDongTeam.change("12点","13点","14点");
        liuDongTeam.change("17点","18点","19点");
        liuDongTeam.remove(oberver1);
        liuDongTeam.change("17点","18点","19点");
    }
}
