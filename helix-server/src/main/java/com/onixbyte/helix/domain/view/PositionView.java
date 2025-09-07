package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.NormalStatus;

import java.io.Serializable;

/**
 * A view object for Position.
 *
 * @author zihluwang
 */
public record PositionView(
        String id,
        String name,
        String code,
        String description,
        Integer sortOrder,
        NormalStatus status
) implements Serializable {
}