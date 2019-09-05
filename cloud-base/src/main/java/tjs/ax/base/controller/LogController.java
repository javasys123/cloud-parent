package tjs.ax.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tjs.ax.base.service.LogService;
import tjs.ax.common.dto.LogDto;
import tjs.ax.common.utils.PageUtils;
import tjs.ax.common.utils.Query;
import tjs.ax.common.utils.Result;

import java.util.Map;

@RequestMapping("/log")
@RestController
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping()
    Result list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return Result.page(new PageUtils(logService.queryList(query), logService.count(query)));
    }

    @PostMapping("/save")
    Result save(@RequestBody LogDto logDto) {
        if (logService.save(logDto) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @DeleteMapping()
    Result remove(Long id) {
        if (logService.remove(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @PostMapping("/batchRemove")
    Result batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = logService.batchRemove(ids);
        if (r > 0) {
            return Result.ok();
        }
        return Result.error();
    }
}
