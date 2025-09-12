package com.onixbyte.helix.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom runtime exception for business logic violations and application-specific errors.
 * <p>
 * This exception is designed to handle business rule violations, validation failures, and other
 * application-specific error conditions that occur during normal operation. Unlike
 * system exceptions, business exceptions are expected and should be handled gracefully by the
 * application's error handling mechanisms.
 * <p>
 * Each business exception carries an HTTP status code that indicates the appropriate response
 * status to return to clients when the exception occurs. This enables consistent error handling
 * across REST API endpoints and provides meaningful HTTP responses to API consumers.
 * <p>
 * Common use cases include:
 * <ul>
 * <li>Resource not found scenarios (404 Not Found)</li>
 * <li>Validation failures (400 Bad Request)</li>
 * <li>Authorisation violations (403 Forbidden)</li>
 * <li>Business rule violations (422 Unprocessable Entity)</li>
 * <li>Conflict situations (409 Conflict)</li>
 * </ul>
 * <p>
 * The exception integrates seamlessly with Spring Boot's exception handling framework and can be
 * caught by global exception handlers to produce standardised error responses.
 *
 * @author zihluwang
 * @see RuntimeException
 * @see org.springframework.http.HttpStatus
 * @since 1.0.0
 */
public class BizException extends RuntimeException {

    /**
     * The HTTP status code associated with this business exception.
     * <p>
     * This status code indicates the appropriate HTTP response status that should be returned to
     * clients when this exception occurs. It enables consistent error handling across
     * REST API endpoints.
     */
    private final HttpStatus status;

    /**
     * Constructs a new business exception with the specified HTTP status and message.
     *
     * @param status  the HTTP status code to associate with this exception
     * @param message the detailed error message explaining the business logic violation
     */
    public BizException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * Returns the HTTP status code associated with this business exception.
     *
     * @return the HTTP status code that should be used in the error response
     */
    public HttpStatus getStatus() {
        return status;
    }
}
