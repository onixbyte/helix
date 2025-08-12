package com.onixbyte.onixboot.web.request;

import java.io.Serializable;

public record MsalLoginRequest(
        String msalToken
) implements Serializable {
}
