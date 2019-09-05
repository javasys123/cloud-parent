package tjs.ax.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import tjs.ax.admin.domain.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

	User get(Long userId);
	
	List<User> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(User user);
	
	int update(User user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

}
