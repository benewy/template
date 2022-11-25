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

import com.beneway.basic.system.sys_filter_user.fo.SysFilterUserFo;
import com.beneway.basic.system.sys_filter_user.service.SysFilterUserService;
import com.beneway.basic.system.sys_filter_user.vo.SysFilterUserVo;
import com.beneway.web.annotation.ReqApi;
import com.restful.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
