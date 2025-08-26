package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizUser;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.entity.UserIdentity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * A MapStruct mapper to convert User domain entities to BizUser business models.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    /**
     * Maps a User entity and a list of UserIdentity entities to a single BizUser business model.
     *
     * @param user           The source User entity. Its fields are mapped directly to BizUser.
     * @param userIdentities The list of source UserIdentity entities.
     * @return A fully populated BizUser object.
     */
    @Mapping(target = "userIdentities", source = "userIdentities")
    BizUser ofBusiness(User user, List<UserIdentity> userIdentities);
}
