package com.shopping.flipkart.dto;

public record UserRequest(String name, String email, String phone, String password) {

   public UserRequest {
      if (name == null || name.isBlank()) {
         throw new IllegalArgumentException("Name cannot be null or blank");
      }
      if (email == null || email.isBlank()) {
         throw new IllegalArgumentException("Email cannot be null or blank");
      }
      if (phone == null || phone.isBlank()) {
         throw new IllegalArgumentException("Phone cannot be null or blank");
      }
      if (password == null || password.isBlank()) {
         throw new IllegalArgumentException("Password cannot be null or blank");
      }
   }
}
