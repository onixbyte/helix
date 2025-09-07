package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.IdentityProvider;

import java.io.Serializable;

/**
 * A view object for UserIdentity.
 *
 * @author zihluwang
 */
public record UserIdentityView(
        String userId,
        IdentityProvider provider,
        String externalId
) implements Serializable {
}