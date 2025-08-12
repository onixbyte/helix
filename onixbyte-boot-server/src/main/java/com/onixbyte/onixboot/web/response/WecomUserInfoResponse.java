package com.onixbyte.onixboot.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Response to Wecom user info.
 *
 * @param errorCode    error code
 * @param errorMessage error message
 * @param wecomOpenId  Wecom user ID
 * @author zihluwang
 */
public record WecomUserInfoResponse(
        @JsonProperty("errcode") Integer errorCode,
        @JsonProperty("errmsg") String errorMessage,
        @JsonProperty("userid") String wecomOpenId
) implements Serializable {
}
