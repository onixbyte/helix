package com.onixbyte.helix.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.onixbyte.helix.client.TokenClient;
import com.onixbyte.helix.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenClient tokenClient;

    public TokenService(TokenClient tokenClient) {
        this.tokenClient = tokenClient;
    }

    public String generateToken(User user) {
        return tokenClient.generateToken(user);
    }

    public DecodedJWT verifyToken(String token) {
        return tokenClient.verifyToken(token);
    }
}
