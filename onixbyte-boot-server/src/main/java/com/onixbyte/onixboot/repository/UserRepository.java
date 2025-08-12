package com.onixbyte.onixboot.repository;

import com.onixbyte.onixboot.model.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    @Select("SELECT * FROM users WHERE wecom_open_id = #{wecomOpenId}")
    User selectByWecomOpenId(@Param("wecomOpenId") String wecomOpenId);
}
