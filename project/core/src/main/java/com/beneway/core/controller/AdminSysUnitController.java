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
