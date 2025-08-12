package com.onixbyte.onixboot.controller;

import com.onixbyte.onixboot.service.UserService;
import com.onixbyte.onixboot.service.WecomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final WecomService wecomService;

    @Autowired
    public UserController(
            UserService userService,
            WecomService wecomService
    ) {
        this.userService = userService;
        this.wecomService = wecomService;
    }
}
