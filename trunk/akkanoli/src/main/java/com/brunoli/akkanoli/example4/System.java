/**
 *  * (C) Copyright 2014 Roy Russo  *  * Licensed under the Apache License,
 * Version 2.0 (the "License");  * you may not use this file except in
 * compliance with the License.  * You may obtain a copy of the License at  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by
 * applicable law or agreed to in writing, software  * distributed under the
 * License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.  * See the License for the
 * specific language governing permissions and  * limitations under the License.
 *  *  
 */
package com.brunoli.akkanoli.example4;

import com.brunoli.akkanoli.example3.*;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.contrib.pattern.ClusterSharding;
import akka.contrib.pattern.ShardRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main runtime class.
 */
public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        ActorSystem system = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        ShardRegion.MessageExtractor messageExtractor = new ShardRegion.MessageExtractor() {

            @Override
            public String entryId(Object message) {
                if (message instanceof Counter.EntryEnvelope) {
                    return String.valueOf(((Counter.EntryEnvelope) message).id);
                } else if (message instanceof Counter.Get) {
                    return String.valueOf(((Counter.Get) message).counterId);
                } else {
                    return null;
                }
            }

            @Override
            public Object entryMessage(Object message) {
                if (message instanceof Counter.EntryEnvelope) {
                    return ((Counter.EntryEnvelope) message).payload;
                } else {
                    return message;
                }
            }

            @Override
            public String shardId(Object message) {
                if (message instanceof Counter.EntryEnvelope) {
                    long id = ((Counter.EntryEnvelope) message).id;
                    return String.valueOf(id % 10);
                } else if (message instanceof Counter.Get) {
                    long id = ((Counter.Get) message).counterId;
                    return String.valueOf(id % 10);
                } else {
                    return null;
                }
            }

        };

        ActorRef startedCounterRegion = ClusterSharding.get(system).start("Counter", Props.create(Counter.class),
                messageExtractor);

        Thread.sleep(5000);

        ActorRef counterRegion = ClusterSharding.get(system).shardRegion("Counter");
        counterRegion.tell(new Counter.Get(100), null);

        counterRegion.tell(new Counter.EntryEnvelope(100,
                Counter.CounterOp.INCREMENT), null);
        counterRegion.tell(new Counter.Get(100), null);

        log.debug("Actor System Shutdown Starting...");

        system.shutdown();
    }
}
