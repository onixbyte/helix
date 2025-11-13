package com.onixbyte.helix.manager;

import com.onixbyte.helix.constant.CacheName;
import com.onixbyte.helix.constant.SettingName;
import com.onixbyte.helix.domain.entity.Setting;
import com.onixbyte.helix.repository.SettingRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthSettingManager {

    private final SettingRepository settingRepository;

    public AuthSettingManager(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Cacheable(cacheNames = CacheName.AUTH_SETTING, key = "'register-enabled'")
    public boolean getRegisterEnabled() {
        return Optional.ofNullable(settingRepository.selectOneByName(SettingName.REGISTER_ENABLED))
                .map(Setting::asBoolean)
                .orElse(false);
    }
}
