package com.onixbyte.onixboot.repository;

import com.onixbyte.onixboot.dataset.biz.BizUser;
import com.onixbyte.onixboot.entities.User;
import com.onixbyte.onixboot.enums.IdentityProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Object relation mapper of user.
 *
 * @author zihluwang
 */
@Repository
public interface UserRepository {

    BizUser selectByUsername(@Param("username") String username);

    /**
     * Find user by third party account.
     *
     * @param provider   account provider
     * @param externalId account ID provided by the provider
     * @return found user
     */
    BizUser selectBizUserByIdentity(
            @Param("provider") IdentityProvider provider,
            @Param("externalId") String externalId
    );

    /**
     * Insert user data into database.
     *
     * @param user user to be added to database
     */
    void insert(@Param("user") User user);

    @Select("""
            SELECT password
            FROM users
            WHERE username = #{username}
            """)
    String selectPasswordByUsername(String username);
}
