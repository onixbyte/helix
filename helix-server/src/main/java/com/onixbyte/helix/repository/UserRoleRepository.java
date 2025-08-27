package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository {

    int insertUserRole(@Param("user") User user, @Param("role") Role role);
}
