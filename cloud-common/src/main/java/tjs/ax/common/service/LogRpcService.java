package tjs.ax.common.service;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import tjs.ax.common.dto.LogDto;
import tjs.ax.common.intercepter.FeignIntercepter;
import tjs.ax.common.utils.Result;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignIntercepter.class)
public interface LogRpcService {
    @Async
    @PostMapping("log/save")
    Result save(LogDto logDto);
}
