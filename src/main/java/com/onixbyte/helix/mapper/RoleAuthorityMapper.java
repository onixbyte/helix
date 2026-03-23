package com.onixbyte.helix.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleAuthorityMapper {

    int deleteByRoleId(@Param("roleId") Long roleId);

    int deleteByAuthorityId(@Param("authorityId") Long authorityId);
}
