package com.mycrawler.mycrawler.juc.threadlocal;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-01 09:32
 **/

public class PersonAction {
    public void execute(){
        Context context = ActionContext.getIntance().get();
        String name = context.getName();
        String idCard=getName(name);
        context.setIdCard(idCard);
    }

    private String getName(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "9999"+name;
    }
}
