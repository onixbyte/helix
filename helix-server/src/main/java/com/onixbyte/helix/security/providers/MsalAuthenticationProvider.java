package com.onixbyte.helix.security.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.entity.UserIdentity;
import com.onixbyte.helix.constant.IdentityProvider;
import com.onixbyte.helix.constant.UserStatus;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.MsalManager;
import com.onixbyte.helix.properties.MsalProperties;
import com.onixbyte.helix.security.data.MsalAuthentication;
import com.onixbyte.helix.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MsalAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(MsalAuthenticationProvider.class);
    private final MsalManager msalManager;
    private final MsalProperties msalProperties;
    private final UserService userService;

    public MsalAuthenticationProvider(
            MsalManager msalManager,
            MsalProperties msalProperties,
            UserService userService
    ) {
        this.msalManager = msalManager;
        this.msalProperties = msalProperties;
        this.userService = userService;
    }

    /**
     * Perform Microsoft Entra ID authentication.
     *
     * @param authentication the authentication request object
     * @return authenticated user information
     * @throws AuthenticationException if authentication failed
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof MsalAuthentication msalAuthentication)) {
            throw new IllegalStateException("Cannot process non-msal authentication.");
        }

        // Decode Json Web Token.
        var decodedToken = JWT.decode(msalAuthentication.getPrincipal());

        // Get public key ID.
        var keyId = decodedToken.getKeyId();

        if (keyId == null) {
            throw new BizException(HttpStatus.BAD_REQUEST, "Missing key ID.");
        }

        // Get public key.
        var publicKey = msalManager.getMsalPublicKey(keyId).generateRSAPublicKey();

        // Generate algorithm with public key.
        var algorithm = Algorithm.RSA256(publicKey, null);

        // Construct token verifier.
        var tenantId = msalProperties.getTenantId();
        var verifier = JWT.require(algorithm)
                .withIssuer("https://login.microsoftonline.com/" + tenantId + "/v2.0")
                .withAudience(msalProperties.getClientId())
                .build();

        try {
            // Do verify.
            verifier.verify(msalAuthentication.getPrincipal());

            // Get Microsoft Entra ID Open ID from token.
            var msalOpenId = decodedToken.getClaim("oid").asString();
            var bizUser = userService.getUserByIdentity(IdentityProvider.MICROSOFT_ENTRA_ID, msalOpenId);

            if (Objects.isNull(bizUser)) {
                // If user does not exist, register automatically.
                var name = decodedToken.getClaim("name").asString();
                var email = decodedToken.getClaim("preferred_username").asString();

                var user = new User();
                user.setUsername(name);
                user.setFullName(name);
                user.setEmail(email);
                user.setStatus(UserStatus.ACTIVE);

                var userIdentity = new UserIdentity();
                userIdentity.setProvider(IdentityProvider.MICROSOFT_ENTRA_ID);
                userIdentity.setExternalId(msalOpenId);

                bizUser = userService.register(user, userIdentity);
            }

            msalAuthentication.setAuthenticated(true);
            msalAuthentication.setUser(bizUser);

            return msalAuthentication;
        } catch (JWTVerificationException e) {
            log.error("Cannot verify MSAL Identification Token.", e);
            throw new BizException(HttpStatus.BAD_REQUEST, "MSAL Identification invalid.");
        }
    }

    /**
     * Returns {@code true} if this {@code AuthenticationProvider} supports the indicated
     * {@code Authentication} object.
     *
     * @param authentication authentication information
     * @return <code>true</code> if the implementation can more closely evaluate the
     * code>Authentication</code> class presented
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return MsalAuthentication.class.isAssignableFrom(authentication);
    }
}
