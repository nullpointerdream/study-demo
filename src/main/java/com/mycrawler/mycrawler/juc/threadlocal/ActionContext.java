package com.mycrawler.mycrawler.juc.threadlocal;

import org.apache.poi.hssf.record.formula.functions.T;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-01 09:58
 **/

public class ActionContext {
    private ActionContext(){

    }
    private final static ThreadLocal<Context> threadLocal=new ThreadLocal() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    static class ContextHolder{
        private final static ActionContext actionContext=new ActionContext();
    }

    public static ActionContext getIntance(){
        return ContextHolder.actionContext;
    }

    public Context get(){
        return threadLocal.get();
    }


}
