package com.onixbyte.onixboot.exception;

import org.springframework.http.HttpStatus;

/**
 * A business exception indicates this Wecom user hasn't registered in our system.
 *
 * @author zihluwang
 */
public class WecomUserNotFoundException extends BizException {

    public WecomUserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
