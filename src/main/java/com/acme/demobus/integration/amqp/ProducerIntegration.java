package com.acme.demobus.integration.amqp;

import org.springframework.stereotype.Service;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ProducerIntegration<T> {
    private AmqpTemplate amqpTemplate;
    @Value("${demobus.rabbitmq.exchange}")
    private String EXCHANGE_NAME;

    public ProducerIntegration(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(T o) {
      try{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(o);
        amqpTemplate.convertSendAndReceive(EXCHANGE_NAME, "", json);
      }catch(JsonProcessingException e){
        e.printStackTrace();
      }
      
    }
}
