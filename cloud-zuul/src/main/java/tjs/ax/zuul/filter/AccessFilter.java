package tjs.ax.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tjs.ax.common.constants.CommonConstants;
import tjs.ax.common.context.FilterContextHandler;
import tjs.ax.common.dto.MenuDto;
import tjs.ax.common.dto.UserToken;
import tjs.ax.common.utils.JSONUtils;
import tjs.ax.common.utils.JwtUtils;
import tjs.ax.common.utils.Result;
import tjs.ax.zuul.admin.MenuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 *@date: 下午3:03
 *       
 *@desc: 前置过滤器
 */
public class AccessFilter extends ZuulFilter {
    @Autowired
    MenuService menuService;

    private String ignorePath = "/api-admin/login";
    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 1000;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI();
        if (isStartWith(requestUri)) {
            return null;
        }
        String accessToken = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        if(null == accessToken || accessToken == ""){
            accessToken = request.getParameter(CommonConstants.TOKEN);
        }
        if (null == accessToken) {
            setFailedRequest(Result.error401(), 200);
            return null;
        }
        try {
            UserToken userToken = JwtUtils.getInfoFromToken(accessToken);
        } catch (Exception e) {
            setFailedRequest(Result.error401(), 200);
            return null;
        }
        FilterContextHandler.setToken(accessToken);
        if(!havePermission(request)){
            setFailedRequest(Result.error403(), 200);
            return null;
        }
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
//        //移除忽略token
        headers.remove("authorization");
        return null;
    }

    /**
     *@date: 下午3:03
     *       
     *@desc: 请求失败处理
     */
    private void setFailedRequest(Object body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        HttpServletResponse response = ctx.getResponse();
        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.write(JSONUtils.beanToJson(body));
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
        ctx.setSendZuulResponse(false);
    }

    /**
     *@desc: 权限
     *
     *@date: 下午3:05
     */
    private boolean havePermission(HttpServletRequest request){
        String currentUrl = request.getRequestURI();
        List<MenuDto> menuDtos = menuService.userMenus();
        for(MenuDto menuDto: menuDtos){
            if(StringUtils.isNotEmpty(currentUrl)&&StringUtils.isNotEmpty(menuDto.getUrl())&&currentUrl.startsWith(menuDto.getUrl())){
                return true;
            }
        }
        return false;
    }

    /**
     *@desc: uri判断
     *
     *@date: 下午3:41
     */
    private boolean isStartWith(String requestUri) {
        for (String s : ignorePath.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return false;
    }
}
