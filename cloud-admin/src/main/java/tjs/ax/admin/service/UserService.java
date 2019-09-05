package tjs.ax.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tjs.ax.admin.domain.Dept;
import tjs.ax.admin.domain.Tree;
import tjs.ax.admin.domain.User;
import tjs.ax.admin.vo.UserVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface UserService {
	User get(Long id);

	List<User> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(User user);

	int update(User user);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exits(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO, User user) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;
	Tree<Dept> getTree();

	/**
	 * 更新个人信息
	 * @param user
	 * @return
	 */
	int updatePersonal(User user);

	/**
	 * 更新个人图片
	 * @param file 图片
	 * @param avatar_data 裁剪信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;
}
