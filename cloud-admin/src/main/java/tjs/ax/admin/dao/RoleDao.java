package tjs.ax.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import tjs.ax.admin.domain.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色
 */
@Mapper
public interface RoleDao {

	Role get(Long roleId);
	
	List<Role> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(Role role);
	
	int update(Role role);
	
	int remove(Long roleId);
	
	int batchRemove(Long[] roleIds);

	List<Long> roleIdsByUserId(Long userId);
}
