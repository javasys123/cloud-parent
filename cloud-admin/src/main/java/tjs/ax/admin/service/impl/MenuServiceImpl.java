package tjs.ax.admin.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tjs.ax.admin.dao.MenuDao;
import tjs.ax.admin.dao.RoleMenuDao;
import tjs.ax.admin.domain.Menu;
import tjs.ax.admin.domain.Tree;
import tjs.ax.admin.service.MenuService;
import tjs.ax.admin.utils.BuildTree;
import tjs.ax.common.dto.RouterDto;

import java.util.*;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;

    /**
     * @param
     * @return 树形菜单
     */
    @Cacheable
    @Override
    public Tree<Menu> getSysMenuTree(Long id) {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menus = userMenus(id);
        for (Menu menu : menus) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(menu.getMenuId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", menu.getUrl());
            attributes.put("icon", menu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Cacheable(value = "permission", key = "#userId")
    @Override
    public List<Menu> userMenus(Long userId) {
        return menuMapper.listMenuByUserId(userId);
    }

    @Override
    @CacheEvict(value = "permission", key = "#userId")
    public boolean clearCache(Long userId) {
        return true;
    }

    @Override
    public List<Menu> list(Map<String, Object> params) {
        List<Menu> menus = menuMapper.list(params);
        return menus;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int remove(Long id) {
        int result = menuMapper.remove(id);
        roleMenuMapper.removeByMenuId(id);
        return result;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int save(Menu menu) {
        int r = menuMapper.save(menu);
        return r;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int update(Menu menu) {
        int r = menuMapper.update(menu);
        return r;
    }

    @Override
    public Menu get(Long id) {
        Menu menu = menuMapper.get(id);
        return menu;
    }

    @Override
    public Tree<Menu> getTree() {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menus = menuMapper.list(new HashMap<>(16));
        for (Menu menu : menus) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(menu.getMenuId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getName());
            tree.setObject(menu);
//			Map<String,Object> map =new HashMap<>(16);
//			map.put("url",menuDO.getUrl());
//			map.put("perms",menuDO.getPerms());
//
//			tree.setAttributes(map);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<Menu> getTree(Long id) {
        // 根据roleId查询权限
        List<Menu> menus = menuMapper.list(new HashMap<String, Object>(16));
        List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
        List<Long> temp = menuIds;
        for (Menu menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menuDOs = menuMapper.list(new HashMap<String, Object>(16));
        for (Menu menu : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(menu.getMenuId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getName());
            Map<String, Object> state = new HashMap<>(16);
            Long menuId = menu.getMenuId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Set<String> listPerms(Long userId) {
        List<String> perms = menuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 获取角色下的权限所有id
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Long> MenuIdsByRoleId(Long roleId) {
        // 根据roleId查询权限,只保留子节点，父节点的选中或半选中状态，前台自动调整
        List<Menu> menus = menuMapper.list(new HashMap<String, Object>(16));
        List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(roleId);
        List<Long> temp = menuIds;
        for (Menu menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        return menuIds;
    }

    /**
     * 用户的路由
     *
     * @return
     */
    @Override
    public List<RouterDto> RouterDTOsByUserId(Long userId) {
        List<Menu> menus = userMenus(userId);
        List<RouterDto> routerDTOs = new ArrayList<>();
        for (Menu menu : menus) {
            RouterDto routerDTO = new RouterDto();
            routerDTO.setId(menu.getMenuId());
            routerDTO.setParentId(menu.getParentId());
            routerDTO.setPath(menu.getUrl());
            routerDTO.setComponent(menu.getComponent());
            routerDTO.setName(menu.getName());
            routerDTO.setIconCls(menu.getIcon());
            routerDTO.setMenuShow(true);
            routerDTO.setChildren(new ArrayList<>());
            routerDTO.setLeaf(null!= menu.getType()&& menu.getType()==1);
            routerDTOs.add(routerDTO);
        }
        return RouterDto.buildList(routerDTOs, 0L);
    }

    @Override
    public List<String> PermsByUserId(Long userId) {
        List<String> permsList = new ArrayList<>();
        List<Menu> menus = userMenus(userId);
        for (Menu menu : menus){
            if(menu.getPerms()!=null && ""!= menu.getPerms()){
                permsList.add(menu.getPerms());
            }
        }
        return permsList;
    }

    @Override
    public List<Tree<Menu>> listMenuTree(Long id) {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menus = menuMapper.listMenuByUserId(id);
        for (Menu menu : menus) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(menu.getMenuId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", menu.getUrl());
            attributes.put("icon", menu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
        return list;
    }

}
