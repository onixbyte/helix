package com.onixbyte.helix.service;

import com.onixbyte.identitygenerator.IdentityGenerator;
import com.onixbyte.helix.domain.biz.BizUser;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.entity.UserIdentity;
import com.onixbyte.helix.enums.IdentityProvider;
import com.onixbyte.helix.mapper.UserIdentityMapper;
import com.onixbyte.helix.mapper.UserMapper;
import com.onixbyte.helix.repository.UserIdentityRepository;
import com.onixbyte.helix.repository.UserRepository;
import com.onixbyte.helix.validation.group.OnCreate;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final UserRepository userRepository;
    private final IdentityGenerator<Long> userIdentityGenerator;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserIdentityMapper userIdentityMapper;
    private final UserIdentityRepository userIdentityRepository;

    public UserService(
            UserRepository userRepository,
            IdentityGenerator<Long> userIdentityGenerator,
            PasswordEncoder passwordEncoder, UserMapper userMapper, UserIdentityMapper userIdentityMapper, UserIdentityRepository userIdentityRepository) {
        this.userRepository = userRepository;
        this.userIdentityGenerator = userIdentityGenerator;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userIdentityMapper = userIdentityMapper;
        this.userIdentityRepository = userIdentityRepository;
    }

    /**
     * Get user by third party account.
     *
     * @return found user
     */
    public BizUser getUserByIdentity(IdentityProvider provider, String externalId) {
        return userRepository.selectBizUserByIdentity(provider, externalId);
    }

    /**
     * User registration.
     *
     * @param user the user who wants to register to our service
     * @return the user's information after registration
     */
    @Transactional(rollbackFor = Throwable.class)
    public BizUser register(@Validated(OnCreate.class) User user, UserIdentity userIdentity) {
        if (Objects.isNull(userIdentity)) {
            var encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }

        // Set user ID.
        var userId = userIdentityGenerator.nextId();
        user.setId(userId);
        userIdentity.setUserId(userId);

        // Execute insert.
        userRepository.insert(user);
        userIdentityRepository.insert(userIdentity);

        return userMapper.ofBusiness(user, List.of(userIdentity));
    }

    /**
     * Get user by username.
     *
     * @param username username
     * @return found user
     */
    public BizUser getUserByUsername(String username) {
        return userRepository.selectByUsername(username);
    }

    public String getPasswordByUsername(String username) {
        return userRepository.selectPasswordByUsername(username);
    }
}
