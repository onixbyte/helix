package com.onixbyte.helix.manager;

import com.onixbyte.helix.constant.CacheName;
import com.onixbyte.helix.constant.SettingName;
import com.onixbyte.helix.domain.entity.Setting;
import com.onixbyte.helix.repository.SettingRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CaptchaSettingManager {

    private final SettingRepository settingRepository;

    public CaptchaSettingManager(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Cacheable(cacheNames = CacheName.CAPTCHA_SETTING, key = "'enabled'")
    public boolean isCaptchaEnabled() {
        return Optional.ofNullable(settingRepository.selectOneByName(SettingName.CAPTCHA_ENABLED))
                .map(Setting::asBoolean)
                .orElse(false);
    }
}
