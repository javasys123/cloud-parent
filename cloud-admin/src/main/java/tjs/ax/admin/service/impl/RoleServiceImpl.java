package tjs.ax.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tjs.ax.admin.dao.RoleDao;
import tjs.ax.admin.dao.RoleMenuDao;
import tjs.ax.admin.dao.UserDao;
import tjs.ax.admin.dao.UserRoleDao;
import tjs.ax.admin.domain.Role;
import tjs.ax.admin.domain.RoleMenu;
import tjs.ax.admin.dto.UserRoleDto;
import tjs.ax.admin.service.RoleService;

import java.util.*;


@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    RoleDao roleMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;

    @Override
    public List<Role> list(Map<String, Object> map) {
        List<Role> roles = roleMapper.list(map);
        return roles;
    }

    @Override
    public int count(Map<String, Object> map) {
        return roleMapper.count(map);
    }


    @Override
    public List<UserRoleDto> list(Long userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<Role> roles = roleMapper.list(new HashMap<>(16));
        List<UserRoleDto> roleDTOS = new ArrayList<>();
        for (Role role : roles) {
            UserRoleDto userRoleDTO = new UserRoleDto();
            userRoleDTO.setId(role.getRoleId());
            userRoleDTO.setName(role.getRoleName());
            userRoleDTO.setChecked(false);
            for (Long roleId : rolesIds) {
                if (Objects.equals(role.getRoleId(), roleId)) {
                    // roleDO.setRoleSign("true");
                    userRoleDTO.setChecked(true);
                    break;
                }
            }
            roleDTOS.add(userRoleDTO);
        }
        return roleDTOS;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    @Override
    public int save(Role role) {
        int count = roleMapper.save(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<RoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu rmDo = new RoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Long id) {
        int count = roleMapper.remove(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public Role get(Long id) {
        Role role = roleMapper.get(id);
        return role;
    }

    @Override
    public int update(Role role) {
        int r = roleMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        if(null!=menuIds){
            Long roleId = role.getRoleId();
            roleMenuMapper.removeByRoleId(roleId);
            List<RoleMenu> rms = new ArrayList<>();
            for (Long menuId : menuIds) {
                RoleMenu rmDo = new RoleMenu();
                rmDo.setRoleId(roleId);
                rmDo.setMenuId(menuId);
                rms.add(rmDo);
            }
            if (rms.size() > 0) {
                roleMenuMapper.batchSave(rms);
            }
        }
        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
        int r = roleMapper.batchRemove(ids);
        return r;
    }

    /**
     * 获取用户的角色id
     *
     * @param userId
     * @return 角色id
     */
    @Override
    public List<Long> RoleIdsByUserId(Long userId) {
        return roleMapper.roleIdsByUserId(userId);
    }

}
