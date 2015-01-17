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
package com.brunoli.akkanoli.example3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
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

        system.actorOf(Props.create(Subscriber.class), "subscriber1");
        //another node
        system.actorOf(Props.create(Subscriber.class), "subscriber2");
        system.actorOf(Props.create(Subscriber.class), "subscriber3");

        Thread.sleep(5000);

        //somewhere else
        ActorRef publisher = system.actorOf(Props.create(Publisher.class), "publisher");
        // after a while the subscriptions are replicated
        publisher.tell("hello", null);

        log.debug("Actor System Shutdown Starting...");

        system.shutdown();
    }
}
