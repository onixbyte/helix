package com.onixbyte.helix.domain.web.response;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Record representing a standardised business exception response.
 * <p>
 * This record encapsulates the essential information returned to clients when business logic
 * exceptions occur within the Helix application. It provides a consistent structure for
 * error communication, ensuring that all business exception responses follow the same format and
 * contain the necessary information for client-side error handling.
 * <p>
 * The response includes a timestamp indicating when the exception occurred and a human-readable
 * message explaining the nature of the error. This standardised format enables consistent error
 * handling across different client applications and API consumers.
 * <p>
 * As a record, this class is immutable and provides automatic implementations of {@code equals()},
 * {@code hashCode()}, and {@code toString()} methods, making it suitable for use in functional
 * programming patterns and ensuring thread safety in concurrent environments.
 * <p>
 * This response entity is typically used by exception handlers and error processing components to
 * communicate business rule violations, validation failures, and other application-specific errors
 * to API clients.
 *
 * @param timestamp the timestamp when the exception occurred, providing temporal context for error
 *                  tracking and debugging
 * @param message   a human-readable explanation of the exception, suitable for display to end users
 *                  or logging purposes
 * @author zihluwang
 * @see Serializable
 * @since 1.0.0
 */
public record BizExceptionResponse(
        LocalDateTime timestamp,
        String message
) implements Serializable {
}
