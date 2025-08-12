package com.onixbyte.onixboot.cache;

import com.onixbyte.onixboot.dataset.biz.MsalPublicKey;
import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.properties.MsalProperties;
import com.onixbyte.onixboot.web.response.MsalPublicKeysResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class MsalCache {

    private final WebClient webClient;
    private final MsalProperties msalProperties;
    private final RedisTemplate<Object, Object> redisTemplate;

    public MsalCache(
            WebClient webClient,
            MsalProperties msalProperties,
            RedisTemplate<Object, Object> redisTemplate) {
        this.webClient = webClient;
        this.msalProperties = msalProperties;
        this.redisTemplate = redisTemplate;
    }

    @Cacheable(cacheNames = "msal::public-key", key = "#keyId")
    public MsalPublicKey getMsalPublicKey(String keyId) {
        // Prepare tenant ID.
        var tenantId = msalProperties.getTenantId();

        // Retrieve public keys from Microsoft.
        var response = webClient.get()
                .uri("https://login.microsoft.com/{tenantId}/discovery/v2.0/keys", tenantId)
                .retrieve()
                .bodyToMono(MsalPublicKeysResponse.class)
                .block();

        // Check response data.
        if (Objects.isNull(response) || response.keys().isEmpty()) {
            throw new BizException(HttpStatus.BAD_GATEWAY, "No response from Microsoft Entra ID.");
        }

        // Prepare result wrapper
        var resultKey = new AtomicReference<MsalPublicKey>();
        for (var key : response.keys()) {
            // Save each key to redis and find the one we want
            redisTemplate.opsForValue().set("msal::public-key::" + key.keyId(), key);
            if (Objects.equals(key.keyId(), keyId)) {
                resultKey.set(key);
            }
        }

        return resultKey.get();
    }
}
