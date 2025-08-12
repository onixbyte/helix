package com.onixbyte.onixboot.exception;

import org.springframework.http.HttpStatus;

public class WeComException extends BizException {

    public WeComException(String message) {
        super(HttpStatus.REQUEST_TIMEOUT, message);
    }
}
