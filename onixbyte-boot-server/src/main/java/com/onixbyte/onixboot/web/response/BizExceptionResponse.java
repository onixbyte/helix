package com.onixbyte.onixboot.web.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record BizExceptionResponse(
       LocalDateTime timestamp,
       String message
) implements Serializable {
}
