package com.onixbyte.onixboot.exception;

import org.springframework.http.HttpStatus;

public class WecomUserNotFoundException extends BizException {

    public WecomUserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
