package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.common.Page;
import com.onixbyte.helix.domain.view.UserView;
import com.onixbyte.helix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('system:user:read')")
    @GetMapping
    public Page<UserView> listUsers() {
        return userService.listUsers();
    }
}
