package com.shopping.flipkart.error;

import com.shopping.flipkart.error.exception.UserNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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
      return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
   }

   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<Object> handleIllegalArgumentsException(IllegalArgumentException ex,
      WebRequest request) {
      return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<Object> handleGenericException(Exception ex,
      WebRequest request) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
   }

   private ResponseEntity<Object> buildResponse(HttpStatus httpStatus, String message,
      WebRequest request) {
      Map<String, Object> error = new HashMap<>();
      error.put("timestamp", LocalDateTime.now());
      error.put("status", httpStatus.value());
      error.put("error", httpStatus.getReasonPhrase());
      error.put("message", message);
      error.put("path", request.getDescription(false).replace("uri=", ""));
      return new ResponseEntity<>(error, httpStatus);
   }
}
