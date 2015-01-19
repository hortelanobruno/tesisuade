/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.rabbitmq.rate;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import java.util.HashMap;
import java.util.Map;

public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDelete(TASK_QUEUE_NAME);
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-max-length", 10000);
        DeclareOk ok = channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, args);

        String message = getMessage(argv);

        int size = 1000;

        for (int i = 0; i < size; i++) {
            channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes());

        }

        ok = channel.queueDeclarePassive(TASK_QUEUE_NAME);

        System.out.println("queue size: " + ok.getMessageCount());

        System.out.println(" [x] Sent '" + message + "'");

//        Thread.sleep(120000L);
        channel.close();
        connection.close();
        System.out.println("FIN.");
        System.exit(0);
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1) {
            return "Hello World!";
        }
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) {
            return "";
        }
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
