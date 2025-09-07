package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.common.Page;
import com.onixbyte.helix.domain.entity.UserIdentity;
import com.onixbyte.helix.domain.view.UserView;
import com.onixbyte.helix.domain.web.request.PageRequest;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.UserManager;
import com.onixbyte.helix.mapper.AuthorityMapper;
import com.onixbyte.helix.mapper.RoleMapper;
import com.onixbyte.helix.mapper.UserIdentityMapper;
import com.onixbyte.helix.repository.*;
import com.onixbyte.helix.utils.PageUtil;
import com.onixbyte.identitygenerator.IdentityGenerator;
import com.onixbyte.helix.domain.biz.BizUser;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.constant.IdentityProvider;
import com.onixbyte.helix.mapper.UserMapper;
import com.onixbyte.helix.validation.group.OnCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

/**
 * User service.
 *
 * @author zihluwang
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final IdentityGenerator<Long> userIdentityGenerator;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleMapper roleMapper;
    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;
    private final UserIdentityRepository userIdentityRepository;
    private final UserIdentityMapper userIdentityMapper;
    private final UserManager userManager;

    public UserService(
            UserRepository userRepository,
            IdentityGenerator<Long> userIdentityGenerator,
            UserMapper userMapper,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository,
            RoleMapper roleMapper,
            AuthorityRepository authorityRepository,
            AuthorityMapper authorityMapper,
            UserIdentityRepository userIdentityRepository, UserIdentityMapper userIdentityMapper, UserManager userManager) {
        this.userRepository = userRepository;
        this.userIdentityGenerator = userIdentityGenerator;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleMapper = roleMapper;
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
        this.userIdentityRepository = userIdentityRepository;
        this.userIdentityMapper = userIdentityMapper;
        this.userManager = userManager;
    }

    /**
     * Get user by third party account.
     *
     * @return found user
     */
    public BizUser getUserByIdentity(IdentityProvider provider, String externalId) {
        return userRepository.queryBizUserByIdentity(provider, externalId);
    }

    /**
     * Local user registration.
     *
     * @param user the user who wants to register to our service
     * @return the user's information after registration
     */
    @CachePut(cacheNames = "user", key = "#user.username")
    @Transactional(rollbackFor = Throwable.class)
    public BizUser registerWithUsernameAndPassword(@Validated(OnCreate.class) User user) {
        // Set user ID.
        var userId = userIdentityGenerator.nextId();
        user.setId(userId);

        // Execute insert.
        var affectedRows = userRepository.insertUser(user);
        if (affectedRows != 1) {
            log.error("Unable to save user.");
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Registration failed due to unknown reason.");
        }

        // Bind default role.
        var defaultRole = roleRepository.queryDefaultRole();
        if (Objects.isNull(defaultRole)) {
            throw new BizException(HttpStatus.BAD_REQUEST, "The default role is not set, and the registration cannot be made.");
        }
        affectedRows = userRoleRepository.insertUserRole(user, defaultRole);
        if (affectedRows != 1) {
            log.error("Role binding failed.");
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Role binding failed.");
        }

        // Query authorities the user have.
        var authorities = authorityRepository.queryAuthorities(defaultRole.getId())
                .stream()
                .map(authorityMapper::ofBusiness)
                .toList();

        // Compose return value.
        var bizUser = userMapper.ofBusiness(user);
        bizUser.setUserIdentities(List.of());
        bizUser.setRoles(List.of(roleMapper.ofBusiness(defaultRole)));
        bizUser.setAuthorities(authorities);
        return bizUser;
    }

    /**
     * IAM user registration.
     *
     * @param user         user information
     * @param userIdentity user IAM information
     * @return the registered user information.
     */
    @Transactional(rollbackFor = Throwable.class)
    public BizUser registerWithThirdPartyAccount(@Validated(OnCreate.class) User user, UserIdentity userIdentity) {
        // Set user ID.
        var userId = userIdentityGenerator.nextId();
        user.setId(userId);
        user.setPassword(null);

        // Execute insert.
        userRepository.insertUser(user);
        userIdentityRepository.insertUserIdentity(userIdentity);

        var bizUser = userMapper.ofBusiness(user);
        bizUser.setUserIdentities(List.of(userIdentityMapper.ofBusiness(userIdentity)));
        return bizUser;
    }

    /**
     * Get user by username.
     *
     * @param username username
     * @return found user
     */
    @Cacheable(cacheNames = "user", key = "#username", unless = "#result == null")
    public BizUser getUserByUsername(String username) {
        return userRepository.queryByUsername(username);
    }

    public Page<UserView> queryUsers(PageRequest pageRequest) {
        var page = PageUtil.<UserView>getPage(pageRequest);
        return userManager.queryUsersPaginated(page);
    }
}
