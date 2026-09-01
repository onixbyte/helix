/*
 * Copyright (c) 2024-2026 OnixByte
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.onixbyte.helix.enumeration;

import com.onixbyte.helix.config.AuthenticationConfig;

/**
 * Enumeration of supported identity providers for user authentication.
 * <p>
 * This enumeration defines the various identity providers that the Helix application supports for
 * user authentication and authorisation. Each provider represents a different authentication
 * mechanism or external identity service that can be used to verify user credentials and establish
 * user sessions.
 * <p>
 * The application supports both local authentication (using internal user database) and external
 * identity providers (such as Microsoft Entra ID) to provide flexible authentication options for
 * different deployment scenarios and organisational requirements.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see AuthenticationConfig
 */
public enum CredentialProvider {

    /**
     * Local identity provider using the application's internal user database.
     * <p>
     * This provider authenticates users against locally stored credentials, typically using
     * username/email and password combinations. User accounts are managed entirely within the Helix
     * application's database.
     */
    LOCAL,

    /**
     * Microsoft Entra ID (formerly Azure Active Directory) identity provider.
     * <p>
     * This provider enables authentication through Microsoft's cloud-based identity and access
     * management service. Users authenticate using their organisational Microsoft accounts,
     * supporting features such as single sign-on (SSO) and multi-factor authentication (MFA).
     */
    MICROSOFT_ENTRA_ID
}
