package com.mycrawler.mycrawler.juc.threadlocal;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-01 09:32
 **/

public class DbAction {
    public void execute(){
        try {
            Thread.sleep(1000L);
            Context context = ActionContext.getIntance().get();
            context.setName(Thread.currentThread().getName()+"hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
