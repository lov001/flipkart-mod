package com.shopping.flipkart.messaging.producer;

import static com.shopping.flipkart.constant.Config.USER_TOPIC;

import com.shopping.flipkart.dto.UserKafkaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventProducer {

   private final KafkaTemplate<String, UserKafkaDTO> kafkaTemplate;

   public void sendUserEvent(UserKafkaDTO message) {
      kafkaTemplate.send(USER_TOPIC, message);
   }
}
