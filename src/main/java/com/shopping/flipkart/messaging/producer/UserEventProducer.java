package com.shopping.flipkart.messaging.producer;

import static com.shopping.flipkart.constant.Config.USER_TOPIC;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventProducer {

   private final KafkaTemplate<String, String> kafkaTemplate;

   public void sendUserEvent(String message) {
      kafkaTemplate.send(USER_TOPIC, message);
   }
}
