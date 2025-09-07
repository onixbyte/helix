package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.domain.biz.BizUser;
import com.onixbyte.helix.domain.biz.BizUserIdentity;
import com.onixbyte.helix.constant.UserStatus;

import java.io.Serializable;
import java.util.List;

/**
 * A response sent to the frontend.
 *
 * @author zihluwang
 */
public record UserView(
        String id,
        String username,
        String fullName,
        String email,
        String countryCode,
        String phoneNumber,
        String avatarUrl,
        UserStatus status,
        Long departmentId,
        Long positionId,
        List<UserIdentityView> userIdentities
) implements Serializable {
}
