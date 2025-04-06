package com.shopping.flipkart.config;

import static com.shopping.flipkart.constant.Config.USER_TOPIC;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

   @Bean
   public NewTopic userEventsTopic() {
      return TopicBuilder.name(USER_TOPIC).partitions(1).replicas(1).build();
   }
}
