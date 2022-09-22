package com.beneway.core.controller;

import com.beneway.basic.system.sys_filter_user.fo.SysFilterUserFo;
import com.beneway.basic.system.sys_filter_user.service.SysFilterUserService;
import com.beneway.basic.system.sys_filter_user.vo.SysFilterUserVo;
import com.beneway.web.annotation.ReqApi;
import com.restful.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by zhy on 2022/3/14 15:06
 */
@RestController
@RequestMapping("/admin_sys_filter_user")
public class AdminSysFilterUserController {

    @Resource
    private SysFilterUserService sysFilterUserService;

    /**
     * 获取用户过滤配置列表
     * @return
     */
    @ReqApi(value = "获取用户过滤配置列表", permission = "sys:filterUser:getList")
    @GetMapping("/getList")
    public Result getList(){
        List<SysFilterUserVo> list = sysFilterUserService.getList();
        return Result.success(list);
    }

    /**
     * 用户过滤配置修改
     * @param sysFilterUserFo
     * @return
     */
    @ReqApi(value = "用户过滤配置修改", permission = "sys:filterUser:edit")
    @PutMapping("/edit")
    public Result edit(@RequestBody SysFilterUserFo sysFilterUserFo) {
        return sysFilterUserService.edit(sysFilterUserFo);
    }

}
