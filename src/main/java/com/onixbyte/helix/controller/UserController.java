package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.request.AddUserRequest;
import com.onixbyte.helix.domain.web.request.QueryUserRequest;
import com.onixbyte.helix.domain.web.request.ResetPasswordRequest;
import com.onixbyte.helix.domain.web.request.UpdateUserRequest;
import com.onixbyte.helix.domain.web.response.UserDetailResponse;
import com.onixbyte.helix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
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
    @PreAuthorize("hasAnyAuthority('system:user:read')")
    @GetMapping
    public Page<UserDetailResponse> queryUsers(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @Validated @ModelAttribute QueryUserRequest request
    ) {
        var pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return userService.queryUserDetailsPage(pageRequest, request);
    }

    /**
     * Get user by user ID.
     *
     * @param userId user ID
     * @return paginated user list
     */
    @PreAuthorize("hasAnyAuthority('system:user:read')")
    @GetMapping("/{userId:\\d+}")
    public UserDetailResponse getUserDetailByUserId(@PathVariable Long userId) {
        return userService.getUserDetailByUserId(userId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:user:write')")
    public ResponseEntity<Void> addUser(@Validated @RequestBody AddUserRequest request) {
        userService.addUser(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Void> editUser(@Validated @RequestBody UpdateUserRequest request) {
        userService.updateUser(request);
        return ResponseEntity.ok(null);
    }

    @PreAuthorize("hasAnyAuthority('system:user:reset-password')")
    @PatchMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@Validated @RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request);
        return ResponseEntity.ok(null);
    }

    @PreAuthorize("hasAnyAuthority('system:user:write')")
    @DeleteMapping("/{userId:\\d+}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(null);
    }
}
