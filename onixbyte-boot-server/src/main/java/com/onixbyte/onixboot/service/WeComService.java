package com.onixbyte.onixboot.service;

import com.onixbyte.onixboot.constants.ExternalHost;
import com.onixbyte.onixboot.properties.WeComProperties;
import com.onixbyte.onixboot.web.response.WeComAccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * WeCom service.
 *
 * @author zihluwang
 */
@Service
public class WeComService {

    private static final Logger log = LoggerFactory.getLogger(WeComService.class);
    private final WebClient webClient;
    private final WeComProperties weComProperties;

    public WeComService(
            WebClient webClient,
            WeComProperties weComProperties
    ) {
        this.webClient = webClient;
        this.weComProperties = weComProperties;
    }

    @Cacheable(cacheNames = "we-com:access-token")
    public WeComAccessTokenResponse getAccessToken() {
        return fetchAccessToken().block();
    }

    /**
     * Fetch WeCom access token.
     *
     * @return WeCom access token
     */
    protected Mono<WeComAccessTokenResponse> fetchAccessToken() {
        log.info("Loading access token from WeCom...");
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
                .doOnSuccess((response) -> {
                    log.info("Fetch access token success, access token is {}", response.accessToken());
                })
                .doOnError((e) -> {
                    log.error("Unable to get access token.");
                });
    }
}
