package com.shopping.flipkart.error;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ErrorResponse {

   private String code;
   private String message;
   private String traceId;
   private String path;
   private LocalDateTime timestamp;

   public ErrorResponse(String code, String message, String traceId, String path, LocalDateTime timestamp) {
      this.code = code;
      this.message = message;
      this.traceId = traceId;
      this.path = path;
      this.timestamp = timestamp;
   }
}
