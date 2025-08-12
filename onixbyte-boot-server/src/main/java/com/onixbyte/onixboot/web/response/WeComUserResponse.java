package com.onixbyte.onixboot.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record WeComUserResponse(
        @JsonProperty("errcode") Integer errorCode,
        @JsonProperty("errmsg") String errorMessage,
        @JsonProperty("userid") String weComOpenId,
        @JsonProperty("name") String name
) implements Serializable {
}
