package com.onixbyte.helix.manager;

import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleManager {

    private final RoleRepository roleRepository;

    public RoleManager(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void validateRoles(List<Long> roleIds) {
        if (!roleRepository.areRolesExisted(roleIds)) {
            throw new BizException(HttpStatus.BAD_REQUEST, "Role does not exist in database.");
        }
    }
}
