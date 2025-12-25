package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.UserRole;
import com.onixbyte.helix.domain.entity.embeddable.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.id.userId = :userId")
    int deleteByUserId(Long userId);
}
