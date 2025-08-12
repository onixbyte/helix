package com.onixbyte.onixboot.processor.password;

import com.onixbyte.onixboot.model.User;

public interface PasswordProcessor {

    boolean supports(User user);

    void process(User user);
}
