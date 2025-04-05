package com.shopping.flipkart.error.exception;

import com.shopping.flipkart.error.ErrorCode;

public class BaseException extends RuntimeException {

   private final ErrorCode errorCode;

   public BaseException(ErrorCode errorCode) {
      super(errorCode.getMessage());
      this.errorCode = errorCode;
   }

   public ErrorCode getErrorCode() {
      return errorCode;
   }
}
