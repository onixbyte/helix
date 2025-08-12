package com.onixbyte.onixboot.exception;

import org.springframework.http.HttpStatus;

/**
 * A business exception that is related to Wecom.
 *
 * @author zihluwang
 */
public class WecomException extends BizException {

    public WecomException(String message) {
        super(HttpStatus.REQUEST_TIMEOUT, message);
    }
}
