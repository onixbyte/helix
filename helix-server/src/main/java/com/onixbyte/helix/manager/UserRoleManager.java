package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.UserRole;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.repository.UserRoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleManager {

    private final UserRoleRepository userRoleRepository;

    public UserRoleManager(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void saveBatch(List<UserRole> userRoles) {
        var affectedRows = userRoleRepository.saveBatch(userRoles);
        if (affectedRows != userRoles.size()) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "User role binding failed.");
        }
    }
}
