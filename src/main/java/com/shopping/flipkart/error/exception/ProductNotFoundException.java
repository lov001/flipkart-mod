package com.shopping.flipkart.error.exception;

public class ProductNotFoundException extends RuntimeException {

   public ProductNotFoundException(String message) {
      super(message);
   }

}
