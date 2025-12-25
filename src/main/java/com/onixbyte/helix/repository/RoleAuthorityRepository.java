package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.RoleAuthority;
import com.onixbyte.helix.domain.entity.embeddable.RoleAuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, RoleAuthorityId> {
}
