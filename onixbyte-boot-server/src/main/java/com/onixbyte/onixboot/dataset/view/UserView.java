package com.onixbyte.onixboot.dataset.view;

import com.onixbyte.onixboot.entities.User;

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
        // todo update view object
        throw new RuntimeException("This feature is not implemented yet.");
    }
}
