package com.onixbyte.onixboot.web.response;

import com.onixbyte.onixboot.dataset.biz.MsalPublicKey;

import java.io.Serializable;
import java.util.List;

public record MsalPublicKeysResponse(
        List<MsalPublicKey> keys
) implements Serializable {
}
