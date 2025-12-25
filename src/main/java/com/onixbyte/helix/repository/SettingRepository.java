package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
    Setting getSettingByName(String name);
}
