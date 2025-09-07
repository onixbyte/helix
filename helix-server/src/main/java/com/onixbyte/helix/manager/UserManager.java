package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.biz.BizUser;
import com.onixbyte.helix.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserManager {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BizUser getUser(Long userId) {
        return userRepository.queryBizUserById(userId);
    }
}
