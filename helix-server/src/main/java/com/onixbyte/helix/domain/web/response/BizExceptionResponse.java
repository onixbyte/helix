package com.onixbyte.helix.domain.web.response;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Business exception response entity.
 *
 * @param timestamp the timestamp of the exception occurrence
 * @param message   explanation of the exception
 * @author zihluwang
 */
public record BizExceptionResponse(
        LocalDateTime timestamp,
        String message
) implements Serializable {
}
