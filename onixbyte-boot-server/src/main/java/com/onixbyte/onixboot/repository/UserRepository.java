package com.onixbyte.onixboot.repository;

import com.onixbyte.onixboot.model.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    @Select("SELECT * FROM users WHERE we_com_open_id = #{weComOpenId}")
    User selectByWeComOpenId(@Param("weComOpenId") String weComOpenId);
}
