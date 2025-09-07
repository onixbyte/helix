package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizUser;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.view.UserView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

/**
 * A MapStruct mapper to convert User domain entities to BizUser business models.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    /**
     * Maps a User entity and a list of UserIdentity entities to a single BizUser business model.
     *
     * @param user           The source User entity. Its fields are mapped directly to BizUser.
     * @return A fully populated BizUser object.
     */
    @Mappings({
            @Mapping(target = "authorities", ignore = true),
            @Mapping(target = "roles", ignore = true)
    })
    BizUser ofBusiness(User user);

    /**
     * Maps User entity to BizUser business object.
     *
     * @param user the User entity
     * @return the BizUser business object
     */
    @Mappings({
            @Mapping(target = "authorities", ignore = true),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "userIdentities", ignore = true)
    })
    BizUser asBusiness(User user);

    /**
     * Maps BizUser business object to UserView.
     *
     * @param bizUser the BizUser business object
     * @return the UserView
     */
    @Mappings({
            @Mapping(target = "id", expression = "java(String.valueOf(bizUser.getId()))")
    })
    UserView asView(BizUser bizUser);
}
