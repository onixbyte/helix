package com.onixbyte.onixboot.dataset.view;

import com.onixbyte.onixboot.model.User;

import java.io.Serializable;

/**
 * A response sent to the frontend.
 *
 * @param id             user's ID
 * @param username       username
 * @param name           user's real name
 * @param msalOpenId     Microsoft Entra ID open ID
 * @param dingTalkOpenId DingTalk open ID
 * @author zihluwang
 */
public record UserView(
        String id,
        String username,
        String name,
        String msalOpenId,
        String dingTalkOpenId
) implements Serializable {

    public static UserView of(User user) {
        return new UserView(String.valueOf(user.getId()), user.getUsername(), user.getName(),
                user.getMsalOpenId(), user.getDingTalkOpenId());
    }
}
