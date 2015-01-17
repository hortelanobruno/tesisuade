/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.akkanoli.example3;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.contrib.pattern.DistributedPubSubExtension;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.contrib.pattern.DistributedPubSubMediator;

public class Subscriber extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public Subscriber() {
        ActorRef mediator = DistributedPubSubExtension.get(getContext().system()).mediator();
        // subscribe to the topic named "content"
        mediator.tell(new DistributedPubSubMediator.Subscribe("content", getSelf()),
                getSelf());
    }

    public void onReceive(Object msg) {
        if (msg instanceof String) {
            log.info("Got: {}", msg);
        } else if (msg instanceof DistributedPubSubMediator.SubscribeAck) {
            log.info("subscribing");
        } else {
            unhandled(msg);
        }
    }
}
