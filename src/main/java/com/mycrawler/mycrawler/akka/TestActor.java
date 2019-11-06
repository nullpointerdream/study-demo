package com.mycrawler.mycrawler.akka;

import akka.actor.*;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import akka.routing.ActorRefRoutee;
import akka.routing.Routee;
import akka.routing.Router;
import akka.routing.ScatterGatherFirstCompletedRoutingLogic;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-05 13:22
 **/

public class TestActor extends AbstractActor {

    private Router router;

    @Override
    public void preStart() {
        List<Routee> routeeList = new ArrayList<>();
        ActorRef cpicRenewInfo = getContext().actorOf(Props.create(HelloActor.class), "CPICRenewInfo");
        routeeList.add(new ActorRefRoutee(cpicRenewInfo));
//        routeeList.add(new ActorRefRoutee(anshRenewInfo));
        FiniteDuration within = FiniteDuration.apply(40, TimeUnit.SECONDS);
        router = new Router(new ScatterGatherFirstCompletedRoutingLogic(within), routeeList);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Terminated.class, t -> {

        }).matchAny(o -> {
            System.out.println("qqqqq"+o);
            router.route(o, getSender());
        }).build();
    }

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("sys");
        ActorRef routerActor = system.actorOf(Props.create(HelloActor.class), "renewInfoRouterActor");
        Timeout timeout = new Timeout(Duration.create(40, TimeUnit.SECONDS));
        Future<Object> fu = Patterns.ask(routerActor, "qqqqq", timeout);
        fu.onComplete(new OnComplete<Object>() {
            @Override
            public void onComplete(Throwable failure, Object success) {
                System.out.println("suceess---"+success);
            }
        }, system.dispatcher());
    }
}
