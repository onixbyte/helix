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

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BizException.class)
    public ResponseEntity<BizExceptionResponse> handleBizException(BizException e) {
        return ResponseEntity.status(e.getStatus())
                .body(new BizExceptionResponse(
                        LocalDateTime.now(),
                        e.getMessage())
                );
    }

    @ExceptionHandler(WecomUserNotFoundException.class)
    public ResponseEntity<BizExceptionResponse> handleBizException(WecomUserNotFoundException e) {
        return ResponseEntity.status(e.getStatus())
                .body(new BizExceptionResponse(
                        LocalDateTime.now(),
                        e.getMessage()
                ));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BizExceptionResponse handleConstraintViolation(ConstraintViolationException ex) {
        // You can format the response nicely here,
        // collecting all violation messages.
        var errorMessage = ex.getConstraintViolations().stream()
                .map((violation) -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));

        return new BizExceptionResponse(
                LocalDateTime.now(),
                errorMessage
        );
    }
}
