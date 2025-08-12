package com.onixbyte.onixboot.exception;

import org.springframework.http.HttpStatus;

public class WeComUserNotFoundException extends BizException {

    public WeComUserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
