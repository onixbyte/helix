package com.onixbyte.onixboot.service;

import com.onixbyte.onixboot.cache.WeComCache;
import com.onixbyte.onixboot.constants.ExternalHost;
import com.onixbyte.onixboot.exception.WeComException;
import com.onixbyte.onixboot.properties.WeComProperties;
import com.onixbyte.onixboot.web.request.WeComSendTextCardRequest;
import com.onixbyte.onixboot.web.response.WeComUserInfoResponse;
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
 * WeCom service.
 *
 * @author zihluwang
 */
@Service
public class WeComService {

    private static final Logger log = LoggerFactory.getLogger(WeComService.class);

    private final WebClient webClient;
    private final WeComCache weComCache;
    private final WeComProperties weComProperties;

    public WeComService(
            WebClient webClient,
            WeComCache weComCache,
            WeComProperties weComProperties
    ) {
        this.webClient = webClient;
        this.weComCache = weComCache;
        this.weComProperties = weComProperties;
    }

    /**
     * Get user information from WeCom.
     *
     * @param code user's authentication code
     * @return WeCom user ID
     * @throws WeComException if WeCom respond with error code is not null
     */
    public String getWeComOpenId(String code) {
        var accessToken = weComCache.getAccessToken().accessToken();
        // Compose URI
        var uri = UriComponentsBuilder.fromUriString(ExternalHost.WE_COM_HOST + "/cgi-bin/auth/getuserinfo")
                .queryParam("access_token", accessToken)
                .queryParam("code", code)
                .build()
                .toUri();

        // Send request
        var response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(WeComUserInfoResponse.class)
                .block();

        if (Objects.isNull(response)) {
            throw new WeComException("Cannot fetch user information from WeCom, response body is null.");
        }

        if (!Objects.equals(response.errorCode(), 0)) {
            throw new WeComException("Cannot fetch user information from WeCom, error code [" +
                    response.errorCode() + "], error message [" + response.errorMessage() + "]");
        }

        return response.weComUserId();
    }

    public void sendRegisterMessage(List<String> audiences) {
        // Compose request URI.
        var uri = UriComponentsBuilder.fromUriString(ExternalHost.WE_COM_HOST + "/cgi-bin/message/send")
                .queryParam("access_token", weComCache.getAccessToken().accessToken())
                .build()
                .toUri();

        // Compose authorisation URI.
        var authorisationUrl = UriComponentsBuilder.fromUriString(ExternalHost.WECHAT_OPEN_PLATFORM_HOST + "/connect/oauth2/authorize")
                .queryParam("appid", weComProperties.getCorporationId())
                .queryParam("redirect_url", "https%3A%2F%2Fboot.onixbyte.dev%2Fapi%2Fauth%2Fregister")
                .queryParam("response_type", "code")
                .queryParam("scope", "snsapi_privateinfo")
                .queryParam("agentid", weComProperties.getAgentId())
                .build()
                .toUriString() + "#wechat_redirect";

        var messageBody = new WeComSendTextCardRequest(
                String.join("|", audiences),
                null,
                null,
                "textcard",
                weComProperties.getAgentId(),
                WeComSendTextCardRequest.ofDetail(
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
