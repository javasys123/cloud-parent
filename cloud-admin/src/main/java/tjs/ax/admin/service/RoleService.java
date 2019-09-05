package tjs.ax.admin.service;

import org.springframework.stereotype.Service;
import tjs.ax.admin.domain.Role;
import tjs.ax.admin.dto.UserRoleDto;

import java.util.List;
import java.util.Map;

@Service
public interface RoleService {

	Role get(Long id);

	List<Role> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(Role role);

	int update(Role role);

	int remove(Long id);

	List<UserRoleDto> list(Long userId);

	int batchremove(Long[] ids);

    /**
     * 获取用户的角色id
     * @param userId
     * @return 角色id
     */
	List<Long> RoleIdsByUserId(Long userId);
}
