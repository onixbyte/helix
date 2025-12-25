package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.UserIdentity;
import com.onixbyte.helix.domain.entity.embeddable.UserIdentityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIdentityRepository extends JpaRepository<UserIdentity, UserIdentityId> {
}
