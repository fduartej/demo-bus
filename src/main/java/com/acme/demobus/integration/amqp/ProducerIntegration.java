package com.acme.demobus.integration.amqp;

import org.springframework.stereotype.Service;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ProducerIntegration<T> {
    private AmqpTemplate amqpTemplate;
    @Value("${demobus.rabbitmq.exchange}")
    private String EXCHANGE_NAME;

    public ProducerIntegration(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(T o) {
      amqpTemplate.convertSendAndReceive(EXCHANGE_NAME, "", o);
    }
}
