package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizUserIdentity;
import com.onixbyte.helix.domain.entity.UserIdentity;
import com.onixbyte.helix.domain.view.UserIdentityView;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserIdentityMapper {

    BizUserIdentity ofBusiness(UserIdentity userIdentity);

    /**
     * Maps UserIdentity entity to BizUserIdentity business object.
     *
     * @param userIdentity the UserIdentity entity
     * @return the BizUserIdentity business object
     */
    @Mappings({
    })
    BizUserIdentity asBusiness(UserIdentity userIdentity);

    /**
     * Maps BizUserIdentity business object to UserIdentityView.
     *
     * @param bizUserIdentity the BizUserIdentity business object
     * @return the UserIdentityView
     */
    @Mappings({
    })
    UserIdentityView asView(BizUserIdentity bizUserIdentity);
}
