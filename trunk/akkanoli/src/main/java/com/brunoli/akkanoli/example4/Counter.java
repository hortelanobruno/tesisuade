/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.akkanoli.example4;

import akka.actor.PoisonPill;
import akka.actor.ReceiveTimeout;
import akka.contrib.pattern.ShardRegion;
import akka.japi.Procedure;
import akka.persistence.UntypedPersistentActor;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.Duration;

public class Counter extends UntypedPersistentActor {

    public static enum CounterOp {

        INCREMENT, DECREMENT
    }

    public static class Get {

        final public long counterId;

        public Get(long counterId) {
            this.counterId = counterId;
        }
    }

    public static class EntryEnvelope {

        final public long id;
        final public Object payload;

        public EntryEnvelope(long id, Object payload) {
            this.id = id;
            this.payload = payload;
        }
    }

    public static class CounterChanged {

        final public int delta;

        public CounterChanged(int delta) {
            this.delta = delta;
        }
    }

    int count = 0;

    // getSelf().path().parent().name() is the type name (utf-8 URL-encoded) 
    // getSelf().path().name() is the entry identifier (utf-8 URL-encoded)
    @Override
    public String persistenceId() {
        return getSelf().path().parent().name() + "-" + getSelf().path().name();
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        context().setReceiveTimeout(Duration.create(120, TimeUnit.SECONDS));
    }

    void updateState(CounterChanged event) {
        count += event.delta;
    }

    @Override
    public void onReceiveRecover(Object msg) {
        if (msg instanceof CounterChanged) {
            updateState((CounterChanged) msg);
        } else {
            unhandled(msg);
        }
    }

    @Override
    public void onReceiveCommand(Object msg) {
        if (msg instanceof Get) {
            getSender().tell(count, getSelf());
        } else if (msg == CounterOp.INCREMENT) {
            persist(new CounterChanged(+1), new Procedure<CounterChanged>() {
                public void apply(CounterChanged evt) {
                    updateState(evt);
                }
            });
        } else if (msg == CounterOp.DECREMENT) {
            persist(new CounterChanged(-1), new Procedure<CounterChanged>() {
                public void apply(CounterChanged evt) {
                    updateState(evt);
                }
            });
        } else if (msg.equals(ReceiveTimeout.getInstance())) {
            getContext().parent().tell(
                    new ShardRegion.Passivate(PoisonPill.getInstance()), getSelf());
        } else {
            unhandled(msg);
        }
    }
}
