package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.Menu;
import com.onixbyte.helix.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuManager {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuManager(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<Menu> selectActiveMenusByUserId(Long userId) {
        return menuMapper.selectActiveMenusByUserId(userId);
    }
}
