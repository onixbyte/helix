package com.onixbyte.onixboot.controller;

import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.exception.WecomUserNotFoundException;
import com.onixbyte.onixboot.web.response.BizExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

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
                        e.getMessage())
                );
    }
}
