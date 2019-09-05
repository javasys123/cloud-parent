package tjs.ax.zuul.admin;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tjs.ax.common.dto.MenuDto;
import tjs.ax.common.intercepter.FeignIntercepter;

import java.util.List;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin", configuration = FeignIntercepter.class)
public interface MenuService {
    @GetMapping("/menu/userMenus")
    List<MenuDto> userMenus();
}
