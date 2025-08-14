package com.onixbyte.onixboot.processor.password;

import com.onixbyte.onixboot.entities.User;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Third party password processor. If a user enabled third-party login such as Microsoft Entra ID,
 * DingTalk and Wecom, then set his password to an empty string({@code ""}).
 *
 * @author zihluwang
 */
@Component
@Order(0)
public class ThirdPartyPasswordProcessor implements PasswordProcessor {

    @Override
    public boolean supports(User user) {
        // todo update supports for third parties
        throw new RuntimeException("This feature has not implemented yet.");
    }

    @Override
    public void process(User user) {
        user.setPassword("");
    }
}
