package com.onixbyte.onixboot.processor.password;

import com.onixbyte.onixboot.model.User;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class PlainPasswordProcessor implements PasswordProcessor {

    private final PasswordEncoder passwordEncoder;

    public PlainPasswordProcessor(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supports(User user) {
        var password = user.getPassword();
        return Objects.nonNull(password) && !password.isBlank();
    }

    @Override
    public void process(User user) {
        var password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
    }
}
