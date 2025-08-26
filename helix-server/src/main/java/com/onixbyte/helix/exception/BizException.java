package com.onixbyte.helix.exception;

import org.springframework.http.HttpStatus;

/**
 * Business exception.
 *
 * @author zihluwang
 */
public class BizException extends RuntimeException {

    private final HttpStatus status;

    public BizException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
