package com.onixbyte.helix.manager;

import com.onixbyte.helix.constant.CacheName;
import com.onixbyte.helix.domain.entity.Setting;
import com.onixbyte.helix.repository.SettingRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SettingManager {

    private final SettingRepository settingRepository;

    public SettingManager(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public Setting getSetting(String name) {
        return settingRepository.selectOneByName(name);
    }

    public Setting getSetting(Long id) {
        return settingRepository.selectOneById(id);
    }
}
