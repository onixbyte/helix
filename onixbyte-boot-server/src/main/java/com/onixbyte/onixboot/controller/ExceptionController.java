package com.onixbyte.onixboot.controller;

import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.exception.WecomUserNotFoundException;
import com.onixbyte.onixboot.web.response.BizExceptionResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Global exception handler.
 *
 * @author zihluwang
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * Catch all {@link BizException}.
     *
     * @param ex a {@link BizException} instance
     * @return response entity with custom HTTP status and detailed message
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
     * Catch all {@link WecomUserNotFoundException}.
     *
     * @param ex a {@link WecomUserNotFoundException} instance
     * @return business exception response with detailed message
     */
    @ExceptionHandler(WecomUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BizExceptionResponse handleBizException(WecomUserNotFoundException ex) {
        return new BizExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage()
        );
    }

    /**
     * Catch all {@link ConstraintViolationException}.
     *
     * @param ex a {@link ConstraintViolationException} instance
     * @return business exception response with detailed message
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BizExceptionResponse handleConstraintViolation(ConstraintViolationException ex) {
        // Collecting all violation messages.
        var errorMessage = ex.getConstraintViolations().stream()
                .map((violation) -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));

        return new BizExceptionResponse(
                LocalDateTime.now(),
                errorMessage
        );
    }
}
