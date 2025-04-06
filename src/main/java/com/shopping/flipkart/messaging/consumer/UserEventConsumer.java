package com.shopping.flipkart.messaging.consumer;

import static com.shopping.flipkart.constant.Config.USER_TOPIC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

   private static final Logger log = LoggerFactory.getLogger(UserEventConsumer.class);

   @KafkaListener(topics = USER_TOPIC, groupId = "flipkart-group")
   public void consume(String message) {
      log.info("Received Kafka message: {}", message);
   }
}
