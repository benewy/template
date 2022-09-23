/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

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
