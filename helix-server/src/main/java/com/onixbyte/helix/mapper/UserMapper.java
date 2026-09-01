package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.database.query.wrapper.QueryUserWrapper;
import com.onixbyte.helix.domain.web.response.UserDetailResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface UserMapper {

    int count(@Param("wrapper") QueryUserWrapper request);

    List<UserDetailResponse> selectListWithDetails(
            @Param("pageable") Pageable pageable,
            @Param("wrapper") QueryUserWrapper wrapper
    );

    UserDetailResponse selectWithDetailByUserId(@Param("id") Long userId);
}
