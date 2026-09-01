package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> selectActiveMenusByUserId(@Param("userId") Long userId);
}
