package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.Menu;
import com.onixbyte.helix.mapper.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuManager {
    private static final Logger log = LoggerFactory.getLogger(MenuManager.class);
    private final MenuMapper menuMapper;

    public MenuManager(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<Menu> selectActiveMenusByUserId(Long userId) {
        return menuMapper.selectActiveMenusByUserId(userId);
    }
}
