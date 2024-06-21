package com.mycrawler.mycrawler.thread.interaput;


/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-23 21:25
 **/

public class interruptTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadService threadService=new ThreadService();
        threadService.excute(()->{
          /*  try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
          while (true){
              System.out.println("living");
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        });
        //Thread.sleep(10000);
        threadService.shutdown(4000);
        Thread.sleep(100000);

    }
}
