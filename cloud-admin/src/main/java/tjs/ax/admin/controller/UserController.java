package tjs.ax.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tjs.ax.admin.domain.User;
import tjs.ax.admin.dto.UserDto;
import tjs.ax.admin.dto.do2dto.UserConvert;
import tjs.ax.admin.service.RoleService;
import tjs.ax.admin.service.UserService;
import tjs.ax.admin.utils.MD5Utils;
import tjs.ax.common.annotation.Log;
import tjs.ax.common.context.FilterContextHandler;
import tjs.ax.common.dto.UserToken;
import tjs.ax.common.utils.PageUtils;
import tjs.ax.common.utils.Query;
import tjs.ax.common.utils.Result;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController {
    @Autowired
	UserService userService;
    @Autowired
	RoleService roleService;

	/**
	 * 登录的当前用户，前台需要验证用户登录的页面可以调用此方法
	 * @return
	 */
    @GetMapping("/currentUser")
	UserToken currentUser(){
		return UserToken.builder().userId(FilterContextHandler.getUserID())
				.username(FilterContextHandler.getUsername())
				.name(FilterContextHandler.getName())
				.build();
	}

	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
    @GetMapping("{id}")
	Result get(@PathVariable("id") Long id ){
		UserDto userDTO = UserConvert.MAPPER.do2dto(userService.get(id));
    	return Result.ok().put("data",userDTO);
	}

	/**
	 * 分页查询用户
	 * @param params
	 * @return
	 */
	@Log("获取用户列表")
    @GetMapping()
	Result listByPage(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<UserDto> userDtos = UserConvert.MAPPER.dos2dtos((userService.list(query)));
        int total = userService.count(query);
        PageUtils pageUtil = new PageUtils(userDtos, total);
        return Result.ok().put("page",pageUtil);
    }

	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	@PostMapping()
	Result save(@RequestBody User user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		return Result.operate(userService.save(user) > 0);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@PutMapping()
	Result update(@RequestBody User user) {
		return Result.operate(userService.update(user) > 0);
	}


	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping()
	Result remove(Long id) {
		return Result.operate (userService.remove(id) > 0);
	}

	@PostMapping("/batchRemove")
	@ResponseBody
	Result batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	@PostMapping("/exits")
	@ResponseBody
	boolean exits(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exits(params);
	}
}
