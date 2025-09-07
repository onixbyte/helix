package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizAuthority;
import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.view.AuthorityView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorityMapper {

    @Mappings({
    })
    BizAuthority ofBusiness(Authority authority);

    /**
     * Maps Authority entity to BizAuthority business object.
     *
     * @param authority the Authority entity
     * @return the BizAuthority business object
     */
    @Mappings({
    })
    BizAuthority asBusiness(Authority authority);

    /**
     * Maps BizAuthority business object to AuthorityView.
     *
     * @param bizAuthority the BizAuthority business object
     * @return the AuthorityView
     */
    @Mappings({
        @Mapping(target = "id", expression = "java(String.valueOf(bizAuthority.getId()))")
    })
    AuthorityView asView(BizAuthority bizAuthority);
}
