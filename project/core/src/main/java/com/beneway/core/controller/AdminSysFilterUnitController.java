package com.beneway.core.controller;

import com.beneway.basic.system.sys_filter_unit.fo.SysFilterUnitFo;
import com.beneway.basic.system.sys_filter_unit.service.SysFilterUnitService;
import com.beneway.basic.system.sys_filter_unit.vo.SysFilterUnitVo;
import com.beneway.web.annotation.ReqApi;
import com.restful.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by zhy on 2022/3/11 15:41
 */
@RestController
@RequestMapping("/admin_sys_filter_unit")
public class AdminSysFilterUnitController {

    @Resource
    private SysFilterUnitService sysFilterUnitService;

    /**
     * 获取单位过滤配置列表
     * @return
     */
    @ReqApi(value = "获取单位过滤配置列表", permission = "sys:filterUnit:getList")
    @GetMapping("/getList")
    public Result getList(){
        List<SysFilterUnitVo> list = sysFilterUnitService.getList();
        return Result.success(list);
    }

    /**
     * 单位过滤配置修改
     * @param sysFilterUnitFo
     * @return
     */
    @ReqApi(value = "单位过滤配置修改", permission = "sys:filterUnit:edit")
    @PutMapping("/edit")
    public Result edit(@RequestBody SysFilterUnitFo sysFilterUnitFo) {
        return sysFilterUnitService.edit(sysFilterUnitFo);
    }

}
