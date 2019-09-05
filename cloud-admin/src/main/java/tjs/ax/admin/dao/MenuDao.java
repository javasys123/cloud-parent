package tjs.ax.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import tjs.ax.admin.domain.Menu;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 */
@Mapper
public interface MenuDao {

	Menu get(Long menuId);
	
	List<Menu> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(Menu menu);
	
	int update(Menu menu);
	
	int remove(Long menuId);
	
	int batchRemove(Long[] menuIds);
	
	List<Menu> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
}
