package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizRole;
import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.domain.view.RoleView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    @Mappings({
    })
    BizRole ofBusiness(Role role);

    /**
     * Maps Role entity to BizRole business object.
     *
     * @param role the Role entity
     * @return the BizRole business object
     */
    @Mappings({
    })
    BizRole asBusiness(Role role);

    /**
     * Maps BizRole business object to RoleView.
     *
     * @param bizRole the BizRole business object
     * @return the RoleView
     */
    @Mappings({
        @Mapping(target = "id", expression = "java(String.valueOf(bizRole.getId()))")
    })
    RoleView asView(BizRole bizRole);
}
