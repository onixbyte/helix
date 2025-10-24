package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Setting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository {

    Setting selectOneById(@Param("id") Long id);

    Setting selectOneByName(@Param("name") String name);

    int save(@Param("setting") Setting setting);
}
