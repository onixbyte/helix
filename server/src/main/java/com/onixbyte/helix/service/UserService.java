package com.onixbyte.helix.service;

import com.onixbyte.common.util.HashUtil;
import com.onixbyte.helix.enumeration.UserStatus;
import com.onixbyte.helix.domain.database.query.wrapper.QueryUserWrapper;
import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.entity.UserRole;
import com.onixbyte.helix.domain.web.request.AddUserRequest;
import com.onixbyte.helix.domain.web.request.QueryUserRequest;
import com.onixbyte.helix.domain.web.request.ResetPasswordRequest;
import com.onixbyte.helix.domain.web.request.EditUserRequest;
import com.onixbyte.helix.domain.web.response.UserDetailResponse;
import com.onixbyte.helix.manager.*;
import com.onixbyte.identitygenerator.IdentityGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserManager userManager;
    private final IdentityGenerator<Long> userIdentityGenerator;
    private final RoleManager roleManager;
    private final UserRoleManager userRoleManager;
    private final ApplicationManager applicationManager;
    private final DepartmentManager departmentManager;
    private final PositionManager positionManager;

    @Autowired
    public UserService(
            UserManager userManager,
            IdentityGenerator<Long> userIdentityGenerator,
            RoleManager roleManager,
            UserRoleManager userRoleManager,
            ApplicationManager applicationManager,
            DepartmentManager departmentManager,
            PositionManager positionManager
    ) {
        this.userManager = userManager;
        this.userIdentityGenerator = userIdentityGenerator;
        this.roleManager = roleManager;
        this.userRoleManager = userRoleManager;
        this.applicationManager = applicationManager;
        this.departmentManager = departmentManager;
        this.positionManager = positionManager;
    }

    public Page<UserDetailResponse> queryUserDetailsPage(Pageable pageable, QueryUserRequest request) {
        var wrapper = new QueryUserWrapper();
        wrapper.setDepartmentId(request.departmentId());
        wrapper.setUsername(request.username());
        wrapper.setRegionAbbreviation(request.regionAbbreviation());
        wrapper.setPhoneNumber(request.phoneNumber());
        wrapper.setStatus(Optional.ofNullable(request.status())
                .filter((status) -> !status.isBlank())
                .map(UserStatus::valueOf)
                .orElse(null));
        wrapper.setCreatedAtStart(request.createdAtStart());
        wrapper.setCreatedAtEnd(request.createdAtEnd());

        return userManager.selectUserDetailsPage(pageable, wrapper);
    }

    @Transactional(rollbackFor = Throwable.class)
    public UserDetailResponse addUser(AddUserRequest request) {
        var createTime = LocalDateTime.now();

        // validate all roles are existed
        if (CollectionUtils.isNotEmpty(request.roleIds())) {
            roleManager.validateRoles(request.roleIds());
        }

        // Get user email or use default email address
        var userEmail = Optional.ofNullable(request.email())
                .orElse(applicationManager.getDefaultEmail());

        // Gravatar is used by default when user didn't set an avatar
        var avatarUrl = Optional.ofNullable(request.avatarUrl())
                .orElseGet(() -> "https://gravatar.com/avatar/" + HashUtil.sha256(userEmail) + "/?d=identicon");

        // Get user status, default to `ACTIVE`
        var status = Optional.ofNullable(request.status())
                .orElse(UserStatus.ACTIVE);

        // Build user information
        var user = userManager.save(User.builder()
                .id(userIdentityGenerator.nextId())
                .username(request.username())
                .fullName(request.fullName())
                .email(request.email())
                .regionAbbreviation(request.regionAbbreviation())
                .phoneNumber(request.phoneNumber())
                .avatarUrl(avatarUrl)
                .status(status)
                .departmentId(request.departmentId())
                .positionId(request.positionId())
                .createdAt(createTime)
                .updatedAt(createTime)
                .build());

        // Get role IDs
        var roleIds = Optional.ofNullable(request.roleIds())
                .filter(CollectionUtils::isNotEmpty)
                .orElseGet(() -> roleManager.getRoles(Role.builder()
                                .defaultValue(true)
                                .build())
                        .stream()
                        .map(Role::getId)
                        .toList());

        // Build bindings
        var userRoleBindings = roleIds
                .stream()
                .map((roleId) -> UserRole.builder()
                        .userId(user.getId())
                        .roleId(roleId)
                        .createdAt(createTime)
                        .build()
                )
                .toList();

        // Save user and role bindings
        userRoleManager.saveBatch(userRoleBindings);

        // Get department and position
        var department = departmentManager.selectById(user.getDepartmentId());
        var position = positionManager.selectById(user.getPositionId());

        // Build response and return
        return UserDetailResponse.builder()
                .user(user)
                .departmentName(department.getName())
                .positionName(position.getName())
                .build();
    }

    @Transactional(rollbackFor = Throwable.class)
    public UserDetailResponse updateUser(Long id, EditUserRequest request) {
        userManager.updateUser(User.builder()
                .id(id)
                .fullName(request.fullName())
                .email(request.email())
                .regionAbbreviation(request.regionAbbreviation())
                .phoneNumber(request.phoneNumber())
                .avatarUrl(request.avatarUrl())
                .status(request.status())
                .departmentId(request.departmentId())
                .positionId(request.positionId())
                .build());
        
        return getUserDetailByUserId(id);
    }

    public UserDetailResponse getUserDetailByUserId(Long userId) {
        return userManager.queryUserDetailByUserId(userId);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void resetPassword(Long id, ResetPasswordRequest request) {
        userManager.updatePasswordById(id, request);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void deleteUser(Long userId) {
        userRoleManager.deleteByUserId(userId);
        userManager.deleteById(userId);
    }

    public UserDetailResponse getDetail(User user) {
        var department = departmentManager.selectById(user.getDepartmentId());
        var position = positionManager.selectById(user.getPositionId());

        return UserDetailResponse.builder()
                .user(user)
                .departmentName(department.getName())
                .positionName(position.getName())
                .build();
    }
}
