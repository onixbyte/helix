package com.onixbyte.onixboot.manager;

import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.model.User;
import com.onixbyte.onixboot.processor.password.PasswordProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Password processor manager, who holds all password processors and test the user one by one.
 *
 * @author zihluwang
 */
@Component
public class PasswordProcessorManager {

    private static final Logger log = LoggerFactory.getLogger(PasswordProcessorManager.class);
    private final List<PasswordProcessor> passwordProcessors;

    @Autowired
    public PasswordProcessorManager(List<PasswordProcessor> passwordProcessors) {
        this.passwordProcessors = passwordProcessors;
    }

    /**
     * Process user's password.
     * <p>
     * If user uses any third party authentication service such as Microsoft Entra ID, DingTalk
     * or Wecom, the password will be to a empty string({@code "")}, if user uses plain password
     * (username and password) to register, the password will be encoded
     * with {@code BCryptPasswordEncoder}.
     *
     * @param user password holder
     */
    public void process(User user) {
        for (var processor : passwordProcessors) {
            if (processor.supports(user)) {
                processor.process(user);
                return;
            }
        }

        log.error("Cannot process user's password. User info = {}", user);
        throw new BizException(HttpStatus.BAD_REQUEST, "Cannot process user's password.");
    }
}
