package com.mycrawler.mycrawler.akka;

import akka.actor.UntypedAbstractActor;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-05 13:19
 **/

public class HelloActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("revice ---" +message);
        getSender().tell("tell", getSelf());
    }
}
