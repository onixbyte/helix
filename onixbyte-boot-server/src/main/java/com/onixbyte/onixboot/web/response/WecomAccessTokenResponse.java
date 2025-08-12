package com.onixbyte.onixboot.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Response to Wecom access token.
 *
 * @param errorCode    error code
 * @param errorMessage error message
 * @param accessToken  Wecom access token
 * @param expiresAfter validity period of the access token, measured in seconds
 * @author zihluwang
 */
public record WecomAccessTokenResponse(
        @JsonProperty("errcode") String errorCode,
        @JsonProperty("errmsg") String errorMessage,
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("expires_in") Integer expiresAfter
) implements Serializable {
}
