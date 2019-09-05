package tjs.ax.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tjs.ax.common.constants.CommonConstants;
import tjs.ax.common.context.FilterContextHandler;
import tjs.ax.common.dto.MenuDto;
import tjs.ax.zuul.admin.MenuService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @version V1.0
 */
@RestController
public class LoginController {
    @Autowired
    MenuService menuService;
    @GetMapping({"/test"})
    List<MenuDto> login(HttpServletRequest request)  {
        FilterContextHandler.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));
        return menuService.userMenus();
    }
}
