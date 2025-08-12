package com.onixbyte.onixboot.dataset.biz;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onixbyte.onixboot.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

/**
 * A data class that represents a Microsoft RSA public key. This key is used to verify the tokens
 * issued by Microsoft.
 *
 * @param keyId    ID of the key
 * @param modulus  the RSA modulus component of the public key
 * @param exponent the RSA public exponent component of the public key
 */
public record MsalPublicKey(
        @JsonProperty("kid") String keyId,
        @JsonProperty("n") String modulus,
        @JsonProperty("e") String exponent
) implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(MsalPublicKey.class);

    public RSAPublicKey generateRSAPublicKey() {
        try {
            var urlDecoder = Base64.getUrlDecoder();

            var modulus = new BigInteger(1, urlDecoder.decode(modulus()));
            var exponent = new BigInteger(1, urlDecoder.decode(exponent()));

            var keySpec = new RSAPublicKeySpec(modulus, exponent);
            var keyFactory = KeyFactory.getInstance("RSA");

            if (keyFactory.generatePublic(keySpec) instanceof RSAPublicKey rsaPublicKey) {
                return rsaPublicKey;
            } else {
                throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot compose RSA Public Key.");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Cannot generate RSA Public Key with given modulus and exponent.", e);
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot generate RSA Public Key.");
        }
    }
}
