/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.rabbitmq.rate;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class Worker {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

//        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(TASK_QUEUE_NAME, false, consumer);

        int count = 0;
        int total = 0;
        long aux = System.currentTimeMillis();
        int i = 0;
        while (i < 1000000) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery(1000);
            if (delivery != null) {
                String message = new String(delivery.getBody());

//            System.out.println(" [x] Received '" + message + "'");
//            doWork(message);
//            System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

                count++;
                if (System.currentTimeMillis() > (aux + 1000L)) {
                    total += count;
                    System.out.println("Rate: " + count + ". total: " + total);
                    count = 0;
                    aux = System.currentTimeMillis();
                }
                i++;
            }
        }
        total += count;
        System.out.println("Rate: " + count + ". total: " + total);
        count = 0;
        aux = System.currentTimeMillis();
        System.out.println("FIN.");
        System.exit(0);
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
