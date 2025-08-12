package com.onixbyte.onixboot.cache;

import com.onixbyte.onixboot.constants.ExternalHost;
import com.onixbyte.onixboot.properties.WecomProperties;
import com.onixbyte.onixboot.web.response.WecomAccessTokenResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WecomCache {

    private final WebClient webClient;
    private final WecomProperties wecomProperties;

    public WecomCache(
            WebClient webClient,
            WecomProperties wecomProperties
    ) {
        this.webClient = webClient;
        this.wecomProperties = wecomProperties;
    }

    /**
     * Get Wecom access token.
     *
     * @return Wecom access token
     */
    @Cacheable(cacheNames = "wecom", key = "'access-token'")
    public WecomAccessTokenResponse getAccessToken() {
        // Compose request URI.
        var uri = UriComponentsBuilder.fromUriString(ExternalHost.WECOM_HOST + "/cgi-bin/gettoken")
                .queryParam("corpid", wecomProperties.getCorporationId())
                .queryParam("corpsecret", wecomProperties.getSecret())
                .build()
                .toUri();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(WecomAccessTokenResponse.class)
                .block();
    }
}
