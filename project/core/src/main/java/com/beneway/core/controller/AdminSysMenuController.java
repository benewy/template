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

import com.beneway.basic.system.sys_menu.fo.SysMenuFo;
import com.beneway.basic.system.sys_menu.service.SysMenuService;
import com.beneway.basic.system.sys_menu.vo.SysMenuVo;
import com.beneway.web.annotation.ReqApi;
import com.restful.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin_sys_menu")
public class AdminSysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 新增菜单
     * @param sysMenuFo
     * @return
     */
    @ReqApi(value = "菜单新增", permission = "sys:menu:add")
    @PostMapping("/add")
    public Result add(@RequestBody SysMenuFo sysMenuFo){
        return sysMenuService.add(sysMenuFo);
    }

    /**
     * 编辑菜单
     * @param sysMenuFo
     * @return
     */
    @ReqApi(value = "菜单编辑", permission = "sys:menu:edit")
    @PutMapping("/edit")
    public Result edit(@RequestBody SysMenuFo sysMenuFo){
        return sysMenuService.edit(sysMenuFo);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @ReqApi(value = "菜单删除", permission = "sys:menu:del")
    @DeleteMapping("/del")
    public Result del(@RequestParam Integer id) {
        return sysMenuService.del(id);
    }

    /**
     * 获取树列表
     * @return
     */
    @ReqApi(value = "获取菜单树列表", permission = "sys:menu:getTreeList")
    @GetMapping("/getTreeList")
    public Result getTreeList(){
        List<SysMenuVo> treeList = sysMenuService.getTreeList();
        return Result.success(treeList);
    }

    /**
     * 获取登录用户菜单
     * @return
     */
    @GetMapping("/getUserMenuList")
    public Result getUserMenuList(){
        List<SysMenuVo> userMenuList = sysMenuService.getUserMenuList();
        return Result.success(userMenuList);
    }

}
