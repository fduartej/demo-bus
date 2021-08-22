package com.acme.demobus.integration.amqp;

import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Service
public class ConsumerIntegration<T> {

    @RabbitListener(queues = "${demobus.rabbitmq.queue}")
    public void receiveMessage(T o) {
      System.out.println("Received Message:" + o);
      System.out.println();
    }
    
}
