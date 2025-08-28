package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.biz.BizUser;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.enums.IdentityProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Object relation mapper of user.
 *
 * @author zihluwang
 */
@Repository
public interface UserRepository {

    BizUser queryByUsername(@Param("username") String username);

    /**
     * Find user by third party account.
     *
     * @param provider   account provider
     * @param externalId account ID provided by the provider
     * @return found user
     */
    BizUser queryBizUserByIdentity(
            @Param("provider") IdentityProvider provider,
            @Param("externalId") String externalId
    );

    /**
     * Insert user data into database.
     *
     * @param user user to be added to database
     */
    int insertUser(@Param("user") User user);

    BizUser queryBizUserById(@Param("id") Long id);

    String queryPasswordByUsername(@Param("username") String username);

    List<User> queryUserList(@Param("offset") Long offset, @Param("pageSize") Long pageSize);

    Long countUsers();
}
