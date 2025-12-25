package com.onixbyte.helix.utils;

import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private static final Logger log = LoggerFactory.getLogger(SecurityUtil.class);

    public static User getCurrentUser() {
        var _details = SecurityContextHolder.getContext()
                .getAuthentication()
                .getDetails();

        if (!(_details instanceof User user)) {
            log.error("Authentication details is {}", _details);
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot retrieve user information from context.");
        }

        return user;
    }
}
