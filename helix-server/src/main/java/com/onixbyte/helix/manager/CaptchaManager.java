package com.onixbyte.helix.manager;

import com.onixbyte.helix.constant.CacheName;
import com.onixbyte.helix.properties.CaptchaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CaptchaManager {

    private final Cache captchaCache;

    @Autowired
    public CaptchaManager(RedisCacheManager cacheManager) {
        var _captchaCache = cacheManager.getCache(CacheName.CAPTCHA);

        if (Objects.isNull(_captchaCache)) {
            throw new IllegalStateException("无法获取缓存数据");
        }

        this.captchaCache = _captchaCache;
    }

    public void setCaptcha(String uuid, String captchaCode) {
        captchaCache.put(uuid, captchaCode);
    }

    public String getCaptcha(String uuid) {
        var captcha = captchaCache.get(uuid, String.class);
        captchaCache.evict(uuid);
        return captcha;
    }
}
