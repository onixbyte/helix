package com.onixbyte.onixboot.exception;

import org.springframework.http.HttpStatus;

public class WecomException extends BizException {

    public WecomException(String message) {
        super(HttpStatus.REQUEST_TIMEOUT, message);
    }
}
