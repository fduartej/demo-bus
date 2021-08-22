package com.acme.demobus.integration.amqp;

import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.Message;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.acme.demobus.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConsumerIntegration<T> {

  
    @RabbitListener(queues = "${demobus.rabbitmq.queue}")
    public void receiveMessage(T o) {
      if(o instanceof Message){
        System.out.println("Received Message:" + o);
        System.out.println();
        Message message = (Message) o;
        String json = new String(message.getBody(), StandardCharsets.UTF_8);
        try{
          ObjectMapper mapper = new ObjectMapper();
          Employee emp =mapper.readValue(json, Employee.class);
          System.out.println("Emp:" + emp);
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    }
    
}
