package com.onixbyte.helix.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository {

    boolean areRolesExisted(@Param("roleIds") List<Long> roleIds);
}
