package com.onixbyte.onixboot.service;

import com.onixbyte.identitygenerator.IdentityGenerator;
import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.manager.PasswordProcessorManager;
import com.onixbyte.onixboot.model.User;
import com.onixbyte.onixboot.repository.UserRepository;
import com.onixbyte.onixboot.validation.group.OnCreate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IdentityGenerator<Long> userIdentityGenerator;
    private final PasswordProcessorManager passwordProcessorManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, IdentityGenerator<Long> userIdentityGenerator, PasswordProcessorManager passwordProcessorManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userIdentityGenerator = userIdentityGenerator;
        this.passwordProcessorManager = passwordProcessorManager;
    }

    public User getUserByMsalOpenId(String msalOpenId) {
        return userRepository.selectByMsalOpenId(msalOpenId);
    }

    public User register(@Validated(OnCreate.class) User user) {
        if (!canRegister(user)) {
            throw new BizException(
                    HttpStatus.CONFLICT,
                    "Username, name, Microsoft Entra ID, Wecom Open ID or DingTalk Open ID is registered."
            );
        }

        passwordProcessorManager.process(user);
        var id = userIdentityGenerator.nextId();
        user.setId(id);

        userRepository.insert(user);

        return user;
    }

    public boolean canRegister(User user) {
        return userRepository.canRegister(user);
    }
}
