package com.onixbyte.onixboot.processor.password;

import com.onixbyte.onixboot.model.User;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Order(0)
public class ThirdPartyPasswordProcessor implements PasswordProcessor {

    @Override
    public boolean supports(User user) {
        var msalOpenId = user.getMsalOpenId();
        if (Objects.isNull(msalOpenId) || msalOpenId.isBlank()) {
            return false;
        }

        var dingTalkOpenId = user.getDingTalkOpenId();
        if (Objects.isNull(dingTalkOpenId) || dingTalkOpenId.isBlank()) {
            return false;
        }

        var wecomOpenId = user.getWecomOpenId();
        return Objects.nonNull(wecomOpenId) && !wecomOpenId.isBlank();
    }

    @Override
    public void process(User user) {
        user.setPassword("");
    }
}
