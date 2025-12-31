package com.onixbyte.helix.manager;

import com.onixbyte.helix.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CaptchaManager {

    private final RedisClient redisClient;

    @Autowired
    public CaptchaManager(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public void setCaptcha(String uuid, String captchaCode) {
        redisClient.set(buildCacheKey(uuid), captchaCode, Duration.ofMinutes(5L));
    }

    public String getCaptcha(String uuid) {
        var key = buildCacheKey(uuid);
        var captcha = redisClient.get(key, String.class);
        redisClient.delete(key);
        return captcha;
    }

    private String buildCacheKey(String uuid) {
        return "captcha::" + uuid;
    }
}
