package com.mycrawler.mycrawler.juc.threadlocal;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-01 09:32
 **/

public class TaskRunable implements Runnable {
    private DbAction dbAction=new DbAction();
    private PersonAction personAction=new PersonAction();

    @Override
    public void run() {
        Context context = ActionContext.getIntance().get();
        dbAction.execute();
        personAction.execute();

        System.out.println("name "+context.getName()+"person "+context.getIdCard());
    }
}
