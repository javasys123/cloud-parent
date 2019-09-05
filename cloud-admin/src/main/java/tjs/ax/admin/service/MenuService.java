package tjs.ax.admin.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import tjs.ax.admin.domain.Menu;
import tjs.ax.admin.domain.Tree;
import tjs.ax.common.dto.RouterDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface MenuService {
	Tree<Menu> getSysMenuTree(Long id);

	/**
	* @desc: 登录用户的权限
	*/
	List<Menu> userMenus(Long userId);

	List<Tree<Menu>> listMenuTree(Long id);

	Tree<Menu> getTree();

	Tree<Menu> getTree(Long id);

	@CacheEvict(value = "permission",key = "#userId")
	boolean clearCache(Long userId);

	List<Menu> list(Map<String, Object> params);

	int remove(Long id);

	int save(Menu menu);

	int update(Menu menu);

	Menu get(Long id);

	Set<String> listPerms(Long userId);

	/**
	 * 获取角色下的权限所有id
	 * @param roleId
	 * @return
	 */
	List<Long> MenuIdsByRoleId(Long roleId);

	/**
	 * 用户的路由
	 * @return
	 */
	List<RouterDto> RouterDTOsByUserId(Long userId);
	/**
	 * 用户权限
	 */
	List<String> PermsByUserId(Long userId);
}
