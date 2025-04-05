package com.shopping.flipkart.error.exception;

import com.shopping.flipkart.error.ErrorCode;

public class UserNotFoundException extends BaseException {

   public UserNotFoundException() {
      super(ErrorCode.USER_NOT_FOUND);
   }

}
