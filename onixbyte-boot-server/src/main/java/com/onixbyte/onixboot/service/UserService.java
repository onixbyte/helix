package com.onixbyte.onixboot.service;

import com.onixbyte.identitygenerator.IdentityGenerator;
import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.manager.PasswordProcessorManager;
import com.onixbyte.onixboot.model.User;
import com.onixbyte.onixboot.repository.UserRepository;
import com.onixbyte.onixboot.validation.group.OnCreate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * User service.
 *
 * @author zihluwang
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final IdentityGenerator<Long> userIdentityGenerator;
    private final PasswordProcessorManager passwordProcessorManager;

    public UserService(
            UserRepository userRepository,
            IdentityGenerator<Long> userIdentityGenerator,
            PasswordProcessorManager passwordProcessorManager
    ) {
        this.userRepository = userRepository;
        this.userIdentityGenerator = userIdentityGenerator;
        this.passwordProcessorManager = passwordProcessorManager;
    }

    /**
     * Use Microsoft Entra ID's Open ID to look up users in the database.
     *
     * @param msalOpenId Microsoft Entra ID's Open ID
     * @return found user
     */
    public User getUserByMsalOpenId(String msalOpenId) {
        return userRepository.selectByMsalOpenId(msalOpenId);
    }

    /**
     * User registration.
     *
     * @param user the user who wants to register to our service
     * @return the user's information after registration
     */
    public User register(@Validated(OnCreate.class) User user) {
        // Check whether the user can register.
        if (!canRegister(user)) {
            throw new BizException(
                    HttpStatus.CONFLICT,
                    "Username, name, Microsoft Entra ID, Wecom Open ID or DingTalk Open ID is registered."
            );
        }

        // Process password.
        passwordProcessorManager.process(user);

        // Set user ID.
        var id = userIdentityGenerator.nextId();
        user.setId(id);

        // Execute insert.
        userRepository.insert(user);

        return user;
    }

    /**
     * Check the database for duplicate usernames, Microsoft Entra ID Open IDs, Wecom Open IDs, and
     * DingTalk Open IDs.
     *
     * @param user the user who wants to register
     * @return {@code true} indicates this user can register, otherwise {@code false}
     */
    public boolean canRegister(User user) {
        return userRepository.canRegister(user);
    }
}
