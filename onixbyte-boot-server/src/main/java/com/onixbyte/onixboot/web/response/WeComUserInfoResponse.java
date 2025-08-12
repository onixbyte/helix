package com.onixbyte.onixboot.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record WeComUserInfoResponse(
        @JsonProperty("errcode") Integer errorCode,
        @JsonProperty("errmsg") String errorMessage,
        @JsonProperty("userid") String weComUserId
) implements Serializable {
}
