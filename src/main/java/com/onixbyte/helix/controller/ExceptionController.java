package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.response.BizExceptionResponse;
import com.onixbyte.helix.exception.BizException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Global exception handler for the Helix application.
 * <p>
 * This controller advice provides centralised exception handling across all controllers in
 * the application. It intercepts exceptions thrown during request processing and converts them into
 * appropriate HTTP responses with standardised error formats.
 * <p>
 * The controller handles various types of exceptions including:
 * <ul>
 *   <li>Business logic exceptions ({@link BizException})</li>
 *   <li>Bean validation constraint violations ({@link ConstraintViolationException})</li>
 * </ul>
 * <p>
 * All error responses are formatted consistently using {@link BizExceptionResponse} to provide a
 * uniform API error structure for client applications.
 *
 * @author zihluwang
 * @see BizException
 * @see BizExceptionResponse
 * @see RestControllerAdvice
 * @since 1.0.0
 */
@RestControllerAdvice
public class ExceptionController {


    /**
     * Handles business logic exceptions thrown throughout the application.
     * <p>
     * This method intercepts {@link BizException} instances and converts them into appropriate HTTP
     * responses. The HTTP status code is determined by the exception's status property, whilst the
     * error message is extracted from the exception and included in the response body.
     * <p>
     * The response includes a timestamp indicating when the error occurred and the specific error
     * message describing the business logic violation.
     *
     * @param ex the business exception that was thrown
     * @return a {@link ResponseEntity} containing the error response with appropriate HTTP status
     * and {@link BizExceptionResponse} body
     * @see BizException
     * @see BizExceptionResponse
     */
    @ExceptionHandler(BizException.class)
    public ResponseEntity<BizExceptionResponse> handleBizException(BizException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new BizExceptionResponse(
                        LocalDateTime.now(),
                        ex.getMessage())
                );
    }

    /**
     * Handles bean validation constraint violation exceptions.
     * <p>
     * This method processes {@link ConstraintViolationException} instances that occur when bean
     * validation constraints are violated during request processing. It extracts all constraint
     * violations, formats them into a readable error message, and returns a standardised
     * error response.
     * <p>
     * The error message includes the property path and violation message for each constraint that
     * was violated, separated by commas for multiple violations. The response is automatically
     * assigned a {@code 400 Bad Request} status.
     *
     * @param ex the constraint violation exception containing validation errors
     * @return a {@link BizExceptionResponse} containing the formatted validation
     * error messages and timestamp
     * @see ConstraintViolationException
     * @see BizExceptionResponse
     * @see jakarta.validation.constraints
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BizExceptionResponse handleConstraintViolation(ConstraintViolationException ex) {
        var errorMessage = ex.getConstraintViolations().stream()
                .map((violation) -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));

        return new BizExceptionResponse(
                LocalDateTime.now(),
                errorMessage
        );
    }
}
