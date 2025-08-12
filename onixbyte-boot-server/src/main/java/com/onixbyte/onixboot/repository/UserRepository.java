package com.onixbyte.onixboot.repository;

import com.onixbyte.onixboot.model.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    @Select("""
            SELECT id, username, name, msal_open_id, ding_talk_open_id, wecom_open_id
            FROM users
            WHERE wecom_open_id = #{wecomOpenId}
            """)
    User selectByWecomOpenId(@Param("wecomOpenId") String wecomOpenId);

    @Select("""
            SELECT id, username, name, msal_open_id, ding_talk_open_id, wecom_open_id
            FROM users
            WHERE msal_open_id = #{msalOpenId}
            """)
    User selectByMsalOpenId(@Param("msalOpenId") String msalOpenId);

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

    @Insert("""
            INSERT INTO users
            VALUES (#{user.id}, #{user.username}, #{user.name}, #{user.password},
                    #{user.msalOpenId}, #{user.dingTalkOpenId}, #{user.wecomOpenId})
            """)
    void insert(@Param("user") User user);
}
