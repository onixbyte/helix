package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizPosition;
import com.onixbyte.helix.domain.entity.Position;
import com.onixbyte.helix.domain.view.PositionView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * A mapper for Position.
 *
 * @author zihluwang
 */
@Mapper(componentModel = "spring")
public interface PositionMapper {

    /**
     * Convert Position entity to BizPosition.
     *
     * @param position the Position entity
     * @return the BizPosition object
     */
    @Mappings({
            // Ignore createdAt and updatedAt fields when converting to business object
    })
    BizPosition asBusiness(Position position);

    /**
     * Convert BizPosition to PositionView.
     *
     * @param bizPosition the BizPosition object
     * @return the PositionView object
     */
    @Mappings({
            @Mapping(target = "id", expression = "java(bizPosition.getId() != null ? bizPosition.getId().toString() : null)")
    })
    PositionView asView(BizPosition bizPosition);
}