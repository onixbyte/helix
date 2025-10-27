package com.onixbyte.helix.manager;

import com.onixbyte.helix.constant.CacheName;
import com.onixbyte.helix.properties.CaptchaProperties;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CaptchaManager {

    private final Cache captchaCache;
    private final CaptchaProperties captchaProperties;

    public CaptchaManager(RedisCacheManager cacheManager, CaptchaProperties captchaProperties) {
        var _captchaCache = cacheManager.getCache(CacheName.CAPTCHA);

        if (Objects.isNull(_captchaCache)) {
            throw new IllegalStateException("无法获取缓存数据");
        }

        this.captchaCache = _captchaCache;
        this.captchaProperties = captchaProperties;
    }

    public void setCaptcha(String uuid, String captchaCode) {
        captchaCache.put(uuid, captchaCode);
    }

    public String getCaptcha(String uuid) {
        var captcha = captchaCache.get(uuid, String.class);
        captchaCache.evict(uuid);
        return captcha;
    }

    public boolean isCaptchaEnabled() {
        return captchaProperties.enabled();
    }
}
