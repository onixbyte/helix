package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.Menu;
import com.onixbyte.helix.domain.model.TreeNode;
import com.onixbyte.helix.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<TreeNode<Menu>> getMenuTree() {
        return menuService.getMenuTree();
    }
}
