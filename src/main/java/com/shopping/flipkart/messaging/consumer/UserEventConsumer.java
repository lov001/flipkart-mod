package com.shopping.flipkart.messaging.consumer;

import static com.shopping.flipkart.constant.Config.USER_TOPIC;

import com.shopping.flipkart.dto.UserKafkaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

   private static final Logger log = LoggerFactory.getLogger(UserEventConsumer.class);

   @KafkaListener(topics = USER_TOPIC, groupId = "flipkart-user-group")
   public void consume(UserKafkaDTO message) {
      log.info("✅ Received user event from Kafka: {}", message);
   }
}
