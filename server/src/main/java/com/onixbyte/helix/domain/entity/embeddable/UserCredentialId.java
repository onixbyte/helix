package com.onixbyte.helix.domain.entity.embeddable;

import com.onixbyte.helix.enumeration.CredentialProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the composite primary key for the UserIdentity entity.
 * <p>
 * This key is composed of the internal user ID, the identity provider, and the external ID
 * from that provider.
 */
@Embeddable
public class UserCredentialId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The identifier of the internal user account, corresponding to the 'user_id' column.
     * <p>
     * This also serves as a foreign key reference to the User entity.
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * The external identity provider, corresponding to the 'provider' column.
     */
    @Column(nullable = false)
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private CredentialProvider provider;

    /**
     * The unique identifier from the external provider, corresponding to the 'external_id' column.
     */
    @Column(nullable = false)
    private String credential;

    // --- Constructors ---

    public UserCredentialId() {
    }

    public UserCredentialId(Long userId, CredentialProvider provider, String credential) {
        this.userId = userId;
        this.provider = provider;
        this.credential = credential;
    }

    // --- Getters and Setters (Omitted for brevity, but should exist) ---
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CredentialProvider getProvider() {
        return provider;
    }

    public void setProvider(CredentialProvider provider) {
        this.provider = provider;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    // --- equals and hashCode (REQUIRED for composite keys) ---
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentialId that = (UserCredentialId) o;
        return Objects.equals(userId, that.userId) && provider == that.provider && Objects.equals(credential, that.credential);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, provider, credential);
    }
}
