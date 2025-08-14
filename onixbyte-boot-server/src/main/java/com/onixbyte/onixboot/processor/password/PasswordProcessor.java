package com.onixbyte.onixboot.processor.password;

import com.onixbyte.onixboot.entities.User;

/**
 * The password processor handles user passwords.
 *
 * @author zihluwang
 */
public interface PasswordProcessor {

    /**
     * Check whether the user's password can be processed.
     *
     * @param user user information
     * @return {@code true} if the user's password can be processed by this password processor
     */
    boolean supports(User user);

    /**
     * Process user's password.
     *
     * @param user user information
     */
    void process(User user);
}
