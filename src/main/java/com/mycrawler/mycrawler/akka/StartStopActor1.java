package com.mycrawler.mycrawler.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

class StartStopActor1 extends AbstractActor {
  static Props props() {
    return Props.create(StartStopActor1.class, StartStopActor1::new);
  }

  @Override
  public void preStart() {
    System.out.println("first started");
    getContext().actorOf(StartStopActor2.props(), "second");
  }

  @Override
  public void postStop() {
    System.out.println("first stopped");
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .matchEquals(
            "stop",
            s -> {
              getContext().stop(getSelf());
            })
        .build();
  }
}

class StartStopActor2 extends AbstractActor {

  static Props props() {
    return Props.create(StartStopActor2.class, StartStopActor2::new);
  }

  @Override
  public void preStart() {
    System.out.println("second started");
  }

  @Override
  public void postStop() {
    System.out.println("second stopped");
  }

  // Actor.emptyBehavior is a useful placeholder when we don't
  // want to handle any messages in the actor.
  @Override
  public Receive createReceive() {
    return receiveBuilder().build();
  }



}