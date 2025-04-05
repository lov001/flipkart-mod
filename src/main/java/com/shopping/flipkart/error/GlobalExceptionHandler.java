package com.shopping.flipkart.error;

import com.shopping.flipkart.error.exception.BaseException;
import com.shopping.flipkart.error.exception.UserNotFoundException;
import java.time.LocalDateTime;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(UserNotFoundException.class)
   public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
      WebRequest request) {
      return buildResponse(ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND, ex.getMessage(),
         request);
   }

   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<Object> handleIllegalArgumentsException(IllegalArgumentException ex,
      WebRequest request) {
      return buildResponse(ErrorCode.INVALID_ARGS, HttpStatus.BAD_REQUEST, ex.getMessage(),
         request);
   }

   @ExceptionHandler(BaseException.class)
   public ResponseEntity<Object> handleBaseException(BaseException ex, WebRequest request) {
      return buildResponse(ex.getErrorCode(), HttpStatus.BAD_REQUEST, ex.getMessage(), request);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<Object> handleGenericException(Exception ex,
      WebRequest request) {
      return buildResponse(ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR,
         ex.getMessage(), request);
   }

   private ResponseEntity<Object> buildResponse(ErrorCode code, HttpStatus httpStatus,
      String message, WebRequest request) {
      ErrorResponse response = new ErrorResponse(
         code.getCode(),
         message,
         MDC.get("traceId"),
         request.getDescription(false).replace("uri=", ""),
         LocalDateTime.now()
      );
      return new ResponseEntity<>(response, httpStatus);
   }
}
