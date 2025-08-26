package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizUserIdentity;
import com.onixbyte.helix.domain.entity.UserIdentity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserIdentityMapper {

    BizUserIdentity ofBusiness(UserIdentity userIdentity);
}
