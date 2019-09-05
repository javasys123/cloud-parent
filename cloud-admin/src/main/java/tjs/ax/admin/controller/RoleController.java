package tjs.ax.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tjs.ax.admin.domain.Role;
import tjs.ax.admin.service.RoleService;
import tjs.ax.common.utils.PageUtils;
import tjs.ax.common.utils.Query;
import tjs.ax.common.utils.Result;

import java.util.List;
import java.util.Map;

@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping()
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Role> roles = roleService.list(query);
        int total = roleService.count(query);
        PageUtils pageUtil = new PageUtils(roles, total);
        return pageUtil;
    }

    @GetMapping("/userId/{userId}")
    List<Long> roleIdByUserId(@PathVariable Long userId){
        return roleService.RoleIdsByUserId(userId);
    }

    @PostMapping
    Result save(@RequestBody Role role){
        if(roleService.save(role)>0){
            return Result.ok();
        }
        return Result.error();
    }

    @PutMapping
    Result update(@RequestBody Role role){
        if(roleService.update(role)>0){
            return Result.ok();
        }
        return Result.error();
    }

}
