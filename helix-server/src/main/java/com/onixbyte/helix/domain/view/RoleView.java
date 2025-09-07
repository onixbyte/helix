package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.NormalStatus;

import java.io.Serializable;

/**
 * A view object for Role.
 *
 * @author zihluwang
 */
public record RoleView(
        String id,
        String name,
        String code,
        String description,
        NormalStatus status
) implements Serializable {
}