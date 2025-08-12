package com.onixbyte.onixboot.web.request;

import java.io.Serializable;

/**
 * Request of login via Microsoft Entra ID.
 *
 * @param msalToken identification token provided by Microsoft
 * @author zihluwang
 */
public record MsalLoginRequest(
        String msalToken
) implements Serializable {
}
