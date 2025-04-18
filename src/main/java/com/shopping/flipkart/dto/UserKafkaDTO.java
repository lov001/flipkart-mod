package com.shopping.flipkart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKafkaDTO {

   private Long id;
   private String username;
   private String email;
   private String phone;
}
