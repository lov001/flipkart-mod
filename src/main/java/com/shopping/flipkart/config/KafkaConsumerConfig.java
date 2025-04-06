package com.shopping.flipkart.config;

import com.shopping.flipkart.dto.UserKafkaDTO;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

   @Value("${spring.kafka.bootstrap-servers}")
   private String bootstrapServers;

   @Bean
   public ConsumerFactory<String, UserKafkaDTO> consumerFactory() {
      Map<String, Object> config = new HashMap<>();
      config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
      config.put(ConsumerConfig.GROUP_ID_CONFIG, "flipkart-user-group");
      config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
      config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
      config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
      return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
         new JsonDeserializer<>(UserKafkaDTO.class));
   }

   @Bean
   public ConcurrentKafkaListenerContainerFactory<String, UserKafkaDTO> kafkaListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String, UserKafkaDTO> factory =
         new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(consumerFactory());
      return factory;
   }
}
