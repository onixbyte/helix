package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.UserCredential;
import com.onixbyte.helix.domain.entity.embeddable.UserCredentialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, UserCredentialId> {
}
