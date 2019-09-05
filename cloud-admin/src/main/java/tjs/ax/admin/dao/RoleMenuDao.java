package tjs.ax.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import tjs.ax.admin.domain.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 */
@Mapper
public interface RoleMenuDao {

	RoleMenu get(Long id);
	
	List<RoleMenu> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(RoleMenu roleMenu);
	
	int update(RoleMenu roleMenu);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	List<Long> listMenuIdByRoleId(Long roleId);
	
	int removeByRoleId(Long roleId);

	int removeByMenuId(Long menuId);
	
	int batchSave(List<RoleMenu> list);
}
