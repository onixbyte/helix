package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.response.BizExceptionResponse;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.utils.MessageUtil;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * This controller advise will catch some business exception which produced when performing actions.
 *
 * @author zihluwang
 * @author siujamo
 * @see BizException
 * @see BizExceptionResponse
 * @see RestControllerAdvice
 * @since 1.0.0
 */
@RestControllerAdvice
public class ExceptionController {

    private final MessageUtil messageUtil;

    public ExceptionController(MessageUtil messageUtil) {
        this.messageUtil = messageUtil;
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<BizExceptionResponse> handleBizException(BizException ex) {
        var message = ex.getMessageCode() == null
                ? ex.getMessage()
                : messageUtil.getMessage(ex.getMessageCode(), ex.getMessageArgs());

        return ResponseEntity.status(ex.getStatus())
                .body(new BizExceptionResponse(
                        LocalDateTime.now(),
                        message)
                );
    }

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BizExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        return new BizExceptionResponse(
                LocalDateTime.now(),
                errorMessage
        );
    }
}
