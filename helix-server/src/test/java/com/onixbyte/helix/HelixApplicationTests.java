package com.onixbyte.helix;

import com.onixbyte.helix.enums.IdentityProvider;
import com.onixbyte.helix.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test"})
class HelixApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        var user = userService.getUserByIdentity(IdentityProvider.LOCAL, "hijklmn");
        System.out.println(user);
    }

}
