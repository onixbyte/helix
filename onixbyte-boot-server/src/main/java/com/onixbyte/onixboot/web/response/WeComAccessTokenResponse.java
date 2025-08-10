package com.onixbyte.onixboot.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record WeComAccessTokenResponse(
        @JsonProperty("errcode") String errorCode,
        @JsonProperty("errmsg") String errorMessage,
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("expires_in") Integer expiresAfter
) implements Serializable {
}
