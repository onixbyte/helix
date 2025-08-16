package com.onixbyte.onixboot.dataset.view;

import com.onixbyte.onixboot.dataset.biz.BizUser;
import com.onixbyte.onixboot.dataset.biz.BizUserIdentity;
import com.onixbyte.onixboot.enums.UserStatus;

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
        List<BizUserIdentity> userIdentities
) implements Serializable {

    public static UserView of(BizUser user) {
        return new UserView(String.valueOf(user.getId()), user.getUsername(), user.getFullName(),
                user.getEmail(), user.getCountryCode(), user.getPhoneNumber(), user.getAvatarUrl(),
                user.getStatus(), user.getDepartmentId(), user.getPositionId(), user.getUserIdentities());
    }
}
