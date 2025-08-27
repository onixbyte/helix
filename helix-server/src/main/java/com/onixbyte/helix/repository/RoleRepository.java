package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {

    Role queryDefaultRole();
}
