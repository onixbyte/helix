package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.Setting;
import com.onixbyte.helix.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SettingManager {

    private final SettingRepository settingRepository;

    @Autowired
    public SettingManager(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Cacheable(cacheNames = "setting", key = "#name")
    public Setting getSettingByName(String name) {
        return settingRepository.getSettingByName(name);
    }
}
