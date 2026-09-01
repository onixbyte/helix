package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.entity.Menu;
import com.onixbyte.helix.domain.common.TreeNode;
import com.onixbyte.helix.manager.MenuManager;
import com.onixbyte.helix.utils.SecurityUtil;
import com.onixbyte.helix.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuManager menuManager;

    @Autowired
    public MenuService(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    public List<TreeNode<Menu>> getMenuTree() {
        var user = SecurityUtil.getCurrentUser();
        var menus = menuManager.selectActiveMenusByUserId(user.getId());
        return TreeUtil.buildForest(menus);
    }
}
