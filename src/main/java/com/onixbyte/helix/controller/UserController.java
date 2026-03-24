package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.request.AddUserRequest;
import com.onixbyte.helix.domain.web.request.EditUserRequest;
import com.onixbyte.helix.domain.web.request.QueryUserRequest;
import com.onixbyte.helix.domain.web.request.ResetPasswordRequest;
import com.onixbyte.helix.domain.web.response.ActionResponse;
import com.onixbyte.helix.domain.web.response.UserDetailResponse;
import com.onixbyte.helix.service.UserService;
import com.onixbyte.helix.shared.MessageName;
import com.onixbyte.helix.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This controller provides entry points to manipulate users.
 *
 * @author zihluwang
 * @author siujamo
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final MessageUtil messageUtil;

    @Autowired
    public UserController(UserService userService, MessageUtil messageUtil) {
        this.userService = userService;
        this.messageUtil = messageUtil;
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

    /**
     * Add a new user.
     *
     * @param request user to be added
     * @return added user
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('system:user:write')")
    public UserDetailResponse addUser(@Validated @RequestBody AddUserRequest request) {
        return userService.addUser(request);
    }

    /**
     * Edit a user.
     *
     * @param request user to be edited
     * @return edited user
     */
    @PutMapping
    public ResponseEntity<Void> editUser(@Validated @RequestBody EditUserRequest request) {
        userService.updateUser(request);
        return ResponseEntity.ok(null);
    }

    /**
     * Reset user's password.
     *
     * @param request reset password request, contains ID of the user and new password
     * @return action response
     */
    @PreAuthorize("hasAnyAuthority('system:user:reset-password')")
    @PatchMapping("/reset-password/{id:\\d+}")
    public ActionResponse resetPassword(
            @PathVariable Long id,
            @Validated @RequestBody ResetPasswordRequest request
    ) {
        userService.resetPassword(id, request);
        return ActionResponse.success(messageUtil.getMessage(MessageName.USER_PASSWORD_RESET_SUCCESS));
    }

    /**
     * Delete a user.
     *
     * @param userId ID of the user to be deleted
     * @return action response
     */
    @PreAuthorize("hasAnyAuthority('system:user:write')")
    @DeleteMapping("/{userId:\\d+}")
    public ActionResponse deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ActionResponse.success(messageUtil.getMessage(MessageName.USER_DELETED, userId));
    }
}
