package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.web.request.AddUserRequest;
import com.onixbyte.helix.domain.web.request.QueryUserRequest;
import com.onixbyte.helix.domain.web.request.UpdateUserRequest;
import com.onixbyte.helix.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get user list.
     *
     * @param pageNum  page number
     * @param pageSize page size
     * @return paginated user list
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:user:read')")
    public Page<User> getUsers(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ModelAttribute QueryUserRequest request
    ) {
        var pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return userService.getUsers(pageRequest, request);
    }

    @GetMapping("/{id:\\d+}")
    @PreAuthorize("hasAnyAuthority('system:user_detail:read') or (isAuthenticated() and authentication.details.id = #id)")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('system:user:write')")
    public User addUser(@Validated @RequestBody AddUserRequest request) {
        return userService.addUser(request);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('system:user:write')")
    public User updateUser(@Validated @RequestBody UpdateUserRequest request) {
        return userService.updateUser(request);
    }
}
