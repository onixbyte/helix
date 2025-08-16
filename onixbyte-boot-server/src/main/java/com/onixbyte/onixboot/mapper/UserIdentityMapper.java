package com.onixbyte.onixboot.mapper;

import com.onixbyte.onixboot.dataset.biz.BizUserIdentity;
import com.onixbyte.onixboot.entities.UserIdentity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserIdentityMapper {

    BizUserIdentity ofBusiness(UserIdentity userIdentity);
}
