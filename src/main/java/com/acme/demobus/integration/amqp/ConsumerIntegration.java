package com.acme.demobus.integration.amqp;

import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Service
public class ConsumerIntegration<T> {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerIntegration.class);

    @RabbitListener(queues = "${demobus.rabbitmq.queue}")
    public void receiveMessage(T o) {
      logger.debug("Received Message: {} ", o);
    }
    
}
