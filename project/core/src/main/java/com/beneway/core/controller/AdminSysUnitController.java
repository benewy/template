package com.beneway.core.controller;

import com.beneway.basic.system.sys_unit.entity.fo.SysUnitFo;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import com.beneway.basic.system.sys_unit.service.SysUnitService;
import com.beneway.web.annotation.ReqApi;
import com.restful.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin_sys_unit")
public class AdminSysUnitController {

    @Resource
    private SysUnitService sysUnitService;

    /**
     * 新增单位
     * @param sysUnitFo
     * @return
     */
    @ReqApi(value = "单位新增", permission = "sys:unit:add")
    @PostMapping("/add")
    public Result add(@RequestBody SysUnitFo sysUnitFo){
        return sysUnitService.add(sysUnitFo);
    }

    /**
     * 编辑单位
     * @param sysUnitFo
     * @return
     */
    @ReqApi(value = "单位新增", permission = "sys:unit:edit")
    @PutMapping("/edit")
    public Result edit(@RequestBody SysUnitFo sysUnitFo){
        return sysUnitService.edit(sysUnitFo);
    }

    /**
     * 单位删除
     * @param id
     * @return
     */
    @ReqApi(value = "单位删除", permission = "sys:unit:del")
    @DeleteMapping("/del")
    public Result del(@RequestParam Integer id) {
        return sysUnitService.del(id);
    }

    /**
     * 获取单位列表
     * @return
     */
    @ReqApi(value = "获取单位列表", permission = "sys:unit:getTreeList")
    @GetMapping("/getTreeList")
    public Result getTreeList(){
        List<SysUnitVo> treeList = sysUnitService.getTreeListByPid(0);
        return Result.success(treeList);
    }

}
