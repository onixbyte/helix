package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizRole;
import com.onixbyte.helix.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    @Mappings({
    })
    BizRole ofBusiness(Role role);
}
