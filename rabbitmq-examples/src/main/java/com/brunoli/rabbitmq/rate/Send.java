/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.rabbitmq.rate;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";

        int size = 1000000;

        for (int i = 0; i < size; i++) {
            message = "Hello World!" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//            System.out.println(channel.waitForConfirms());
        }

//        Thread.sleep(10000L);
//        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
        System.out.println("FIN.");
        System.exit(0);
    }
}
