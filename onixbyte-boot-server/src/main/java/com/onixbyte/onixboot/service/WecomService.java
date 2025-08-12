package com.onixbyte.onixboot.service;

import com.onixbyte.onixboot.cache.WecomCache;
import com.onixbyte.onixboot.constants.ExternalHost;
import com.onixbyte.onixboot.exception.WecomException;
import com.onixbyte.onixboot.properties.WecomProperties;
import com.onixbyte.onixboot.web.request.WecomSendTextCardRequest;
import com.onixbyte.onixboot.web.response.WecomUserInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Wecom service.
 *
 * @author zihluwang
 */
@Service
public class WecomService {

    private static final Logger log = LoggerFactory.getLogger(WecomService.class);

    private final WebClient webClient;
    private final WecomCache wecomCache;
    private final WecomProperties wecomProperties;

    public WecomService(
            WebClient webClient,
            WecomCache wecomCache,
            WecomProperties wecomProperties
    ) {
        this.webClient = webClient;
        this.wecomCache = wecomCache;
        this.wecomProperties = wecomProperties;
    }

    /**
     * Get user information from Wecom.
     *
     * @param code user's authentication code
     * @return Wecom user ID
     * @throws WecomException if Wecom respond with error code is not null
     */
    public String getWecomOpenId(String code) {
        var accessToken = wecomCache.getAccessToken().accessToken();
        // Compose URI
        var uri = UriComponentsBuilder.fromUriString(ExternalHost.WECOM_HOST + "/cgi-bin/auth/getuserinfo")
                .queryParam("access_token", accessToken)
                .queryParam("code", code)
                .build()
                .toUri();

        // Send request
        var response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(WecomUserInfoResponse.class)
                .block();

        if (Objects.isNull(response)) {
            throw new WecomException("Cannot fetch user information from Wecom, response body is null.");
        }

        if (!Objects.equals(response.errorCode(), 0)) {
            throw new WecomException("Cannot fetch user information from Wecom, error code [" +
                    response.errorCode() + "], error message [" + response.errorMessage() + "]");
        }

        return response.wecomOpenId();
    }

    public void sendRegisterMessage(List<String> audiences) {
        // Compose request URI.
        var uri = UriComponentsBuilder.fromUriString(ExternalHost.WECOM_HOST + "/cgi-bin/message/send")
                .queryParam("access_token", wecomCache.getAccessToken().accessToken())
                .build()
                .toUri();

        // Compose authorisation URI.
        var authorisationUrl = UriComponentsBuilder.fromUriString(ExternalHost.WECHAT_OPEN_PLATFORM_HOST + "/connect/oauth2/authorize")
                .queryParam("appid", wecomProperties.getCorporationId())
                .queryParam("redirect_url", "https%3A%2F%2Fboot.onixbyte.dev%2Fapi%2Fauth%2Fregister")
                .queryParam("response_type", "code")
                .queryParam("scope", "snsapi_privateinfo")
                .queryParam("agentid", wecomProperties.getAgentId())
                .build()
                .toUriString() + "#wechat_redirect";

        var messageBody = new WecomSendTextCardRequest(
                String.join("|", audiences),
                null,
                null,
                "textcard",
                wecomProperties.getAgentId(),
                WecomSendTextCardRequest.ofDetail(
                        "Please click this card to finish registration.",
                        "Please click this card on your mobile phone to complete registration.",
                        authorisationUrl,
                        null
                ),
                null,
                null,
                null
        );

        var sendMessageResponse = webClient.post()
                .uri(uri)
                .bodyValue(messageBody)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<HashMap<String, Object>>() {
                })
                .block();
        return;
    }
}
