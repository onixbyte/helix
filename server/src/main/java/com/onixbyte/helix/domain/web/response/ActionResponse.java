package com.onixbyte.helix.domain.web.response;

public record ActionResponse(
        String message,
        boolean success
) {

    public static ActionResponse success(String message) {
        return new ActionResponse(message, true);
    }

    public static ActionResponse failed(String message) {
        return new ActionResponse(message, false);
    }
}
