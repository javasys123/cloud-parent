package tjs.ax.common.intercepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import tjs.ax.common.constants.CommonConstants;
import tjs.ax.common.context.FilterContextHandler;

public class FeignIntercepter implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //设置通用文件头信息(token)
        requestTemplate.header(CommonConstants.CONTEXT_TOKEN, FilterContextHandler.getToken());
    }
}