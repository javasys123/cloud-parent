package tjs.ax.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import tjs.ax.admin.domain.UserRole;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 */
@Mapper
public interface UserRoleDao {

	UserRole get(Long id);

	List<UserRole> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserRole userRole);

	int update(UserRole userRole);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Long> listRoleId(Long userId);

	int removeByUserId(Long userId);

	int batchSave(List<UserRole> list);

	int batchRemoveByUserId(Long[] ids);
}
