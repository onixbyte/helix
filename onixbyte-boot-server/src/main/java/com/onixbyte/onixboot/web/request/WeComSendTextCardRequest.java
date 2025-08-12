package com.onixbyte.onixboot.web.request;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record WeComSendTextCardRequest(
        @JsonProperty("touser") String recipients,
        @JsonProperty("toparty") String recipientDepartments,
        @JsonProperty("totag") String recipientTags,
        @JsonProperty("msgtype") String messageType,
        @JsonProperty("agentid") Long agentId,
        @JsonProperty("textcard") TextCardDetail textCardDetail,
        @JsonProperty("enable_id_trans") Integer enableIdTranslation,
        @JsonProperty("enable_duplicate_check") Integer enableDuplicateCheck,
        @JsonProperty("duplicateCheckInterval") Integer duplicateCheckInterval
) implements Serializable {

    public static TextCardDetail ofDetail(
            String title,
            String description,
            String url,
            String buttonText
    ) {
        return new TextCardDetail(title, description, url, buttonText);
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public record TextCardDetail(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("url") String url,
            @JsonProperty("btntxt") String buttonText
    ) {
    }
}


