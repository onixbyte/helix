package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.database.query.wrapper.QueryRoleWrapper;
import com.onixbyte.helix.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface RoleMapper {

    boolean areRolesExisted(@Param("roleIds") List<Long> roleIds);

    List<Role> selectAll(
            @Param("pageable") Pageable page,
            @Param("wrapper") QueryRoleWrapper wrapper
    );

    Integer count(@Param("wrapper") QueryRoleWrapper wrapper);
}
