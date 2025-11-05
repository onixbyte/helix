package com.onixbyte.helix.service;

import com.onixbyte.helix.constant.AssetPrefix;
import com.onixbyte.helix.constant.UserStatus;
import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.entity.UserRole;
import com.onixbyte.helix.domain.web.request.AddUserRequest;
import com.onixbyte.helix.domain.web.request.QueryUserRequest;
import com.onixbyte.helix.domain.web.request.UpdateUserRequest;
import com.onixbyte.helix.manager.RoleManager;
import com.onixbyte.helix.manager.UserManager;
import com.onixbyte.helix.manager.UserRoleManager;
import com.onixbyte.helix.properties.AssetProperties;
import com.onixbyte.helix.security.authentication.UsernamePasswordAuthentication;
import com.onixbyte.helix.utils.SecurityUtil;
import com.onixbyte.identitygenerator.IdentityGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserManager userManager;
    private final IdentityGenerator<Long> userIdentityGenerator;
    private final AssetProperties assetProperties;
    private final RoleManager roleManager;
    private final UserRoleManager userRoleManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserManager userManager,
            IdentityGenerator<Long> userIdentityGenerator,
            AssetProperties assetProperties,
            RoleManager roleManager,
            UserRoleManager userRoleManager,
            PasswordEncoder passwordEncoder) {
        this.userManager = userManager;
        this.userIdentityGenerator = userIdentityGenerator;
        this.assetProperties = assetProperties;
        this.roleManager = roleManager;
        this.userRoleManager = userRoleManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<User> getUsers(Pageable pageable, QueryUserRequest request) {
        return userManager.queryPage(pageable, request);
    }

    @Transactional(rollbackFor = Throwable.class)
    public User addUser(AddUserRequest request) {
        var createTime = LocalDateTime.now();

        // validate all roles are existed
        roleManager.validateRoles(request.roleIds());

        var avatarUrl = Optional.ofNullable(request.avatarUrl())
                .orElseGet(() -> assetProperties.publicHost() + AssetPrefix.AVATARS + "/default-avatar-min.jpeg");
        var status = Optional.ofNullable(request.status())
                .orElse(UserStatus.ACTIVE);

        var user = userManager.save(User.builder()
                .id(userIdentityGenerator.nextId())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .fullName(request.fullName())
                .email(request.email())
                .countryCode(request.countryCode())
                .phoneNumber(request.phoneNumber())
                .avatarUrl(avatarUrl)
                .status(status)
                .departmentId(request.departmentId())
                .positionId(request.positionId())
                .createdAt(createTime)
                .updatedAt(createTime)
                .build());

        if (!request.roleIds().isEmpty()) {
            var userRoleBindings = request.roleIds()
                    .stream()
                    .map((roleId) -> UserRole.builder()
                            .userId(user.getId())
                            .roleId(roleId)
                            .createdAt(createTime)
                            .build()
                    )
                    .toList();

            userRoleManager.saveBatch(userRoleBindings);
        }

        return user;
    }

    public User getUser(Long id) {
        var user = userManager.queryById(id);
        // Hide user password.
        user.setPassword(null);
        return user;
    }

    public User updateUser(UpdateUserRequest request) {
        var createTime = LocalDateTime.now();

        var user = userManager.save(User.builder()
                .id(request.id())
                .username(request.username())
                .fullName(request.fullName())
                .email(request.email())
                .countryCode(request.countryCode())
                .phoneNumber(request.phoneNumber())
                .avatarUrl(request.avatarUrl())
                .status(request.status())
                .departmentId(request.departmentId())
                .positionId(request.positionId())
                .updatedAt(createTime)
                .build());
        return userManager.update(user);
    }
}
