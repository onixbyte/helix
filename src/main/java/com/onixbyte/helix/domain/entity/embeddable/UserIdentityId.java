package com.onixbyte.helix.domain.entity.embeddable;

import com.onixbyte.helix.constant.IdentityProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
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
public class UserIdentityId implements Serializable {

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
    private IdentityProvider provider;

    /**
     * The unique identifier from the external provider, corresponding to the 'external_id' column.
     */
    @Column(nullable = false)
    private String externalId;

    // --- Constructors ---

    public UserIdentityId() {
    }

    public UserIdentityId(Long userId, IdentityProvider provider, String externalId) {
        this.userId = userId;
        this.provider = provider;
        this.externalId = externalId;
    }

    // --- Getters and Setters (Omitted for brevity, but should exist) ---
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public IdentityProvider getProvider() {
        return provider;
    }

    public void setProvider(IdentityProvider provider) {
        this.provider = provider;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    // --- equals and hashCode (REQUIRED for composite keys) ---
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserIdentityId that = (UserIdentityId) o;
        return Objects.equals(userId, that.userId) && provider == that.provider && Objects.equals(externalId, that.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, provider, externalId);
    }
}
