package com.onixbyte.onixboot.repository;

import com.onixbyte.onixboot.model.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Object relation mapper of user.
 *
 * @author zihluwang
 */
@Repository
public interface UserRepository {

    /**
     * Find user by Wecom open ID.
     *
     * @param wecomOpenId Wecom open ID
     * @return found user
     */
    @Select("""
            SELECT id, username, name, msal_open_id, ding_talk_open_id, wecom_open_id
            FROM users
            WHERE wecom_open_id = #{wecomOpenId}
            """)
    User selectByWecomOpenId(@Param("wecomOpenId") String wecomOpenId);

    /**
     * Find user by Microsoft Entra ID open ID.
     *
     * @param msalOpenId Microsoft Entra ID open ID
     * @return found user
     */
    @Select("""
            SELECT id, username, name, msal_open_id, ding_talk_open_id, wecom_open_id
            FROM users
            WHERE msal_open_id = #{msalOpenId}
            """)
    User selectByMsalOpenId(@Param("msalOpenId") String msalOpenId);

    /**
     * Check whether user can register.
     *
     * @param user user to be registered
     * @return {@code true} if no duplicated username, name, Microsoft Entra ID open ID, DingTalk
     * open ID and Wecom open ID
     */
    @Select("""
            <script>
            SELECT COUNT(*) = 0
            FROM users
            <where>
                 username = #{user.username}
                 OR name = #{user.name}
                 <if test="user.msalOpenId != null and user.msalOpenId != ''">
                     OR msal_open_id = #{user.msalOpenId}
                 </if>
                 <if test="user.wecomOpenId != null and user.wecomOpenId != ''">
                     OR wecom_open_id = #{user.wecomOpenId}
                 </if>
                 <if test="user.dingTalkOpenId != null and user.dingTalkOpenId != ''">
                     OR ding_talk_open_id = #{user.dingTalkOpenId}
                 </if>
            </where>
            </script>
            """)
    boolean canRegister(@Param("user") User user);

    /**
     * Insert user data into database.
     *
     * @param user user to be added to database
     */
    @Insert("""
            INSERT INTO users
            VALUES (#{user.id}, #{user.username}, #{user.name}, #{user.password},
                    #{user.msalOpenId}, #{user.dingTalkOpenId}, #{user.wecomOpenId})
            """)
    void insert(@Param("user") User user);
}
