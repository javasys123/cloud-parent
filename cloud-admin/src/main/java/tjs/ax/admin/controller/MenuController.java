package tjs.ax.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tjs.ax.admin.domain.Menu;
import tjs.ax.admin.domain.Tree;
import tjs.ax.admin.service.MenuService;
import tjs.ax.common.annotation.Log;
import tjs.ax.common.context.FilterContextHandler;
import tjs.ax.common.dto.MenuDto;
import tjs.ax.common.utils.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/menu")
@RestController()
public class MenuController {
    @Autowired
    MenuService menuService;

    @Log("访问菜单")
    @GetMapping("tree")
    Tree<Menu> tree(){
        return menuService.getTree();
    }
    @GetMapping
    List<Tree<Menu>>  list(){
        return menuService.getTree().getChildren();
    }

    @GetMapping("{id}")
    Menu get(@PathVariable("id") Long id) {
        Menu menu = menuService.get(id);
        return menu;
    }

    @GetMapping("list")
    List<Menu> list(@RequestParam Map<String, Object> params) {
        List<Menu> menus = menuService.list(params);
        return menus;
    }

    @PutMapping()
    Result update(@RequestBody Menu menu){
        if(menuService.update(menu)>0){
            return Result.ok();
        }
        return  Result.error();
    }
    @PostMapping
    Result save(@RequestBody Menu menu){
        return Result.operate(menuService.save(menu)>0);
    }

    @DeleteMapping()
    Result remove(Long id){
        if(menuService.remove(id)>0){
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping("userMenus")
    List<MenuDto> userMenus(){
        List<Menu> menus = menuService.userMenus(Long.parseLong(FilterContextHandler.getUserID()));
        List<MenuDto> menuDtos = new ArrayList<>();
        for (Menu menu : menus){
            MenuDto menuDto = new MenuDto();
            menuDto.setMenuId(menu.getMenuId());
            menuDto.setUrl(menu.getUrl());
            menuDto.setPerms(menu.getPerms());
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }

    @GetMapping("clearCache")
    Result clearCache(){
        Boolean flag = menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        if (flag){
            return  Result.ok();
        }
        return Result.error();
    }

    /**
     * 当前用户菜单的树形结构
     * @return
     */
    @RequestMapping("/currentUserMenus")
    List<Tree<Menu>> currentUserMenus() {
        List<Tree<Menu>> menus = menuService.listMenuTree(Long.parseLong(FilterContextHandler.getUserID()));
        return menus;
    }

    @GetMapping("/roleId")
    List<Long> menuIdsByRoleId(Long roleId){
        return menuService.MenuIdsByRoleId(roleId);
    }
}
