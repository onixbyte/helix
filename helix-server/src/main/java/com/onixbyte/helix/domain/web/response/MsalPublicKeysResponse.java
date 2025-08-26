package com.onixbyte.helix.domain.web.response;

import com.onixbyte.helix.domain.biz.MsalPublicKey;

import java.io.Serializable;
import java.util.List;

/**
 * The response of Microsoft's public keys.
 *
 * @param keys the public keys
 * @author zihluwang
 */
public record MsalPublicKeysResponse(
        List<MsalPublicKey> keys
) implements Serializable {
}
