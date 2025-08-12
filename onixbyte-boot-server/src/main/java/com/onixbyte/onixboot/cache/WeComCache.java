package com.onixbyte.onixboot.cache;

import com.onixbyte.onixboot.constants.ExternalHost;
import com.onixbyte.onixboot.properties.WeComProperties;
import com.onixbyte.onixboot.service.WeComService;
import com.onixbyte.onixboot.web.response.WeComAccessTokenResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeComCache {

    private final WebClient webClient;
    private final WeComProperties weComProperties;

    public WeComCache(
            WebClient webClient,
            WeComProperties weComProperties
    ) {
        this.webClient = webClient;
        this.weComProperties = weComProperties;
    }

    /**
     * Get WeCom access token.
     *
     * @return WeCom access token
     */
    @Cacheable(cacheNames = "we-com", key = "'access-token'")
    public WeComAccessTokenResponse getAccessToken() {
        // Compose request URI.
        var uri = UriComponentsBuilder.fromUriString(ExternalHost.WE_COM_HOST + "/cgi-bin/gettoken")
                .queryParam("corpid", weComProperties.getCorporationId())
                .queryParam("corpsecret", weComProperties.getSecret())
                .build()
                .toUri();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(WeComAccessTokenResponse.class)
                .block();
    }
}
