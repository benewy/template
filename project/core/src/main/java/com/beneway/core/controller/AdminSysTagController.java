package com.beneway.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.system.sys_tag.enums.SysTagTypeEnum;
import com.beneway.basic.system.sys_tag.fo.SysTagFo;
import com.beneway.basic.system.sys_tag.fo.SysTagQueryFo;
import com.beneway.basic.system.sys_tag.po.SysTag;
import com.beneway.basic.system.sys_tag.service.SysTagService;
import com.beneway.basic.system.sys_tag.vo.SysTagVo;
import com.beneway.web.annotation.ReqApi;
import com.restful.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin_sys_tag")
public class AdminSysTagController {


    @Resource
    private SysTagService sysTagService;

    /**
     * 新增标签
     * @param sysTagFo
     * @return
     */
    @ReqApi(value = "标签新增", permission = "sys:tag:add")
    @PostMapping("/add")
    public Result add(@RequestBody SysTagFo sysTagFo){
        return sysTagService.add(sysTagFo);
    }

    /**
     * 编辑标签
     * @param sysTagFo
     * @return
     */
    @ReqApi(value = "编辑标签", permission = "sys:tag:edit")
    @PutMapping("/edit")
    public Result edit(@RequestBody SysTagFo sysTagFo){
        return sysTagService.edit(sysTagFo);
    }

    /**
     * 标签删除
     * @param id
     * @return
     */
    @ReqApi(value = "标签删除", permission = "sys:tag:del")
    @DeleteMapping("/del")
    public Result del(@RequestParam Integer id) {
        return sysTagService.del(id);
    }

    /**
     * 根据类型获取标签列表
     * @param type
     * @return
     */
    @ReqApi(value = "根据类型获取标签列表")
    @GetMapping("getListByType")
    public Result getListByType(@RequestParam String type){
        SysTagTypeEnum typeEnum = SysTagTypeEnum.getByType(type);
        List<SysTag> list = sysTagService.getListByType(typeEnum);
        return Result.success(list);
    }

    /**
     * 标签分页查询
     * @param sysTagQueryFo
     * @return
     */
    @ReqApi(value = "标签管理分页查询", permission = "sys:tag:queryPage")
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody SysTagQueryFo sysTagQueryFo){
        Page<SysTagVo> page = sysTagService.queryPage(sysTagQueryFo);
        return Result.success(page);
    }

}
