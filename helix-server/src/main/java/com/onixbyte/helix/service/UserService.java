package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.manager.UserManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserManager userManager;

    public UserService(UserManager userManager) {
        this.userManager = userManager;
    }

    public Page<User> getUsers(Pageable pageable) {
        return userManager.queryPage(pageable);
    }
}
