package com.mycrawler.mycrawler.thread.atomic;

import java.util.concurrent.TimeUnit;

/**
 * @program: mycrawler
 * @description: volatile 保证可见性
 * @author: 陈家乐
 * @create: 2019-03-05 10:20
 **/

public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {
        Threaddemo threaddemo =new Threaddemo();
        new Thread(threaddemo).start();
        while(true){
            if(threaddemo.isFlag()){
                System.out.println("------------------");
                break;
            }
           //System.out.println(1);
        }



    }



}

class Threaddemo implements Runnable {

    private boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        flag = true;

        System.out.println("flag=" + isFlag());

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

