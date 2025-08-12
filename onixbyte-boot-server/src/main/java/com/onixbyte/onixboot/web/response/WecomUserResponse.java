package com.onixbyte.onixboot.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record WecomUserResponse(
        @JsonProperty("errcode") Integer errorCode,
        @JsonProperty("errmsg") String errorMessage,
        @JsonProperty("userid") String wecomOpenId,
        @JsonProperty("name") String name
) implements Serializable {
}
