package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.NormalStatus;

import java.io.Serializable;

/**
 * A view object for Authority.
 *
 * @author zihluwang
 */
public record AuthorityView(
        String id,
        String code,
        String name,
        String description,
        NormalStatus status
) implements Serializable {
}