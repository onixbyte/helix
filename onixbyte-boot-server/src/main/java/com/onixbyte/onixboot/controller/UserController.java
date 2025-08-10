package com.onixbyte.onixboot.controller;

import com.onixbyte.onixboot.service.UserService;
import com.onixbyte.onixboot.service.WeComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final WeComService weComService;

    @Autowired
    public UserController(
            UserService userService,
            WeComService weComService
    ) {
        this.userService = userService;
        this.weComService = weComService;
    }
}
