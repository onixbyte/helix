package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.RoleAuthority;
import com.onixbyte.helix.domain.entity.embeddable.RoleAuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, RoleAuthorityId> {
    @Modifying
    @Query("DELETE FROM RoleAuthority ra WHERE ra.id.roleId = :roleId")
    int deleteByRoleId(Long roleId);
}
