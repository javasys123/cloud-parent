package tjs.ax.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import tjs.ax.admin.domain.User;
import tjs.ax.admin.service.MenuService;
import tjs.ax.admin.service.TokenService;
import tjs.ax.admin.service.UserService;
import tjs.ax.admin.utils.MD5Utils;
import tjs.ax.common.annotation.Log;
import tjs.ax.common.context.FilterContextHandler;
import tjs.ax.common.dto.LoginDto;
import tjs.ax.common.dto.UserToken;
import tjs.ax.common.utils.JwtUtils;
import tjs.ax.common.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RefreshScope
@RequestMapping
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MenuService menuService;

    @Value("${spring.jackson.date-format}")
    private String value;

    @Log("登录")
    @PostMapping("/login")
    Result login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        String username = loginDto.getUsername().trim();
        String password = loginDto.getPwd().trim();
        password = MD5Utils.encrypt(username, password);
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        List<User> users = userService.list(param);
        if(users.size()<1){
            return Result.error("用户或密码错误");
        }
        User user = users.get(0);
        if (null == user || !user.getPassword().equals(password)) {
            return Result.error("用户或密码错误");
        }
        UserToken userToken = new UserToken(user.getUsername(), user.getUserId().toString(), user.getName());
        String token="";
        try {
            token = JwtUtils.generateToken(userToken, 2*60*60*1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //首先清除用户缓存权限
        menuService.clearCache(user.getUserId());
        // String token = tokenService.createToken(userDO.getUserId());
        return Result.ok("登录成功")
                .put("token", token).put("user", user)
                .put("perms",menuService.PermsByUserId(user.getUserId()))
                .put("router",menuService.RouterDTOsByUserId(user.getUserId()));
    }


    @RequestMapping("/logout")
    Result logout(HttpServletRequest request, HttpServletResponse response) {
        menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        return Result.ok();
    }

    @GetMapping("/login-test")
    public String test(){
        return value;
    }

}
