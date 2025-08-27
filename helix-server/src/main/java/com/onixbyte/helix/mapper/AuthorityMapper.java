package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizAuthority;
import com.onixbyte.helix.domain.entity.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorityMapper {

    @Mappings({
    })
    BizAuthority ofBusiness(Authority authority);
}
