package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository {
    int saveBatch(@Param("userRoles") List<UserRole> userRoles);
}
