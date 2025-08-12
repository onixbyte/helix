package com.onixbyte.onixboot.dataset.view;

import com.onixbyte.onixboot.model.User;

import java.io.Serializable;

public record UserView(
        Long id,
        String username,
        String name,
        String msalOpenId,
        String dingTalkOpenId,
        String wecomOpenId
) implements Serializable {

    public static UserView of(User user) {
        return new UserView(user.getId(), user.getUsername(), user.getName(), user.getMsalOpenId(),
                user.getDingTalkOpenId(), user.getWecomOpenId());
    }
}
