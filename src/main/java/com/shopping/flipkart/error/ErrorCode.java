package com.shopping.flipkart.error;

public enum ErrorCode {

   USER_NOT_FOUND("USR001", "User not found"),
   EMAIL_ALREADY_EXISTS("USR002", "Email already exists"),
   INVALID_CREDENTIALS("USR003", "Invalid credentials"),
   USERNAME_ALREADY_EXISTS("USR004", "Username already exists"),
   INVALID_ARGS("ARG001", "Invalid arguments"),
   INTERNAL_SERVER_ERROR("XXX001", "Internal server error");

   private final String code;
   private final String message;

   ErrorCode(String code, String message) {
      this.code = code;
      this.message = message;
   }

   public String getCode() {
      return code;
   }

   public String getMessage() {
      return message;
   }
}
