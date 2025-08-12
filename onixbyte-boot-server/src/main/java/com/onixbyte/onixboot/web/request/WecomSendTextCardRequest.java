package com.onixbyte.onixboot.web.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * A request that to send a direct message in the form of text card.
 *
 * @param recipients             the user who will receive this direct message
 * @param recipientDepartments   the users who are in the department will receive this direct message
 * @param recipientTags          the users who are in the tag will receive this direct message
 * @param messageType            message type, always {@code textcard} here
 * @param agentId                application ID
 * @param textCardDetail         text card detail
 * @param enableIdTranslation    whether ID translation is turned on, 0 means off and 1 means on
 * @param enableDuplicateCheck   whether to check duplicate message, 0 means off and 1 means on
 * @param duplicateCheckInterval interval of check duplicate message and it is in milliseconds
 * @author zihluwang
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record WecomSendTextCardRequest(
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

    /**
     * Create a text card detail.
     *
     * @param title       title of the card, must not be null
     * @param description content of the card, must not be null
     * @param url         the link to be redirected after clicking the card
     * @param buttonText  text of the card button, default is {@code "Detail"}
     */
    public static TextCardDetail ofDetail(
            String title,
            String description,
            String url,
            String buttonText
    ) {
        return new TextCardDetail(title, description, url, buttonText);
    }

    /**
     * Text card detail.
     *
     * @param title       title of the card, must not be null
     * @param description content of the card, must not be null
     * @param url         the link to be redirected after clicking the card
     * @param buttonText  text of the card button, default is {@code "Detail"}
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public record TextCardDetail(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("url") String url,
            @JsonProperty("btntxt") String buttonText
    ) {
    }
}


