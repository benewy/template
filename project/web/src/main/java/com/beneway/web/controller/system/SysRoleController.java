package com.beneway.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.basic.system.sys_role.po.SysRole;
import com.beneway.basic.system.sys_role.service.SysRoleService;
import com.restful.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/3/26 11:48
 */
@RestController
@RequestMapping("sys_role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 获取前端公共回显组件信息
     * @param id
     * @return
     */
    @GetMapping("/getComRoleInfo")
    public Result getComRoleInfo(@RequestParam Integer id){
        SysRole sysRole = sysRoleService.getOne(new LambdaQueryWrapper<SysRole>()
                .select(SysRole::getType, SysRole::getTitle, SysRole::getRemark)
                .eq(SysRole::getId, id));
        return Result.success(sysRole);
    }

}
