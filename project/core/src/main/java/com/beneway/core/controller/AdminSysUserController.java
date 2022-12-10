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

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.system.sys_unit.entity.po.SysUnit;
import com.beneway.basic.system.sys_user.entity.fo.SysUserFo;
import com.beneway.basic.system.sys_user.entity.fo.SysUserQueryFo;
import com.beneway.basic.system.sys_user.entity.vo.SysUserVo;
import com.beneway.basic.system.sys_user.service.SysUserService;
import com.beneway.basic.utils.login_user.LoginUserUtils;
import com.beneway.core.entity.sys_user.fo.LoginFo;
import com.beneway.core.service.sys_user.AdminSysUserService;
import com.beneway.web.annotation.ReqApi;
import com.restful.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin_sys_user")
public class AdminSysUserController {

    @Resource
    private AdminSysUserService adminSysUserService;

    @Resource
    private SysUserService sysUserService;

    /**
     * 浙政钉扫码登录
     *
     * @param code
     * @return
     */
    @PostMapping("/qrLogin")
    public Result qrLogin(String code) {
        return adminSysUserService.qrLogin(code);
    }

    /**
     * 登录
     *
     * @param loginFo
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFo loginFo) {
        Result login = adminSysUserService.login(loginFo);
        return login;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("/logout")
    public Result logout() {
        String loginUserId = LoginUserUtils.getLoginUserId();
        // 清空缓存
        sysUserService.clearLoginUserInfoCacheable(loginUserId);
        StpUtil.logout();

        return Result.success();
    }

    /**
     * 用户新增
     *
     * @param sysUserFo
     * @return
     */
    @ReqApi(value = "用户新增", permission = "sys:user:add")
    @PostMapping("/add")
    public Result add(@RequestBody SysUserFo sysUserFo) {
        return sysUserService.add(sysUserFo);
    }

    /**
     * 用户编辑
     *
     * @param sysUserFo
     * @return
     */
    @ReqApi(value = "用户编辑", permission = "sys:user:edit")
    @PutMapping("/edit")
    public Result edit(@RequestBody SysUserFo sysUserFo) {
        return sysUserService.edit(sysUserFo);
    }

    /**
     * 用户删除
     *
     * @param userId
     * @return
     */
    @ReqApi(value = "用户删除", permission = "sys:user:del")
    @DeleteMapping("/del")
    public Result del(@RequestParam String userId) {
        return sysUserService.del(userId);
    }

    /**
     * 获取用户管理分页查询 单位列表
     * @return
     */
    @ReqApi(value = "获取用户管理分页查询单位列表", permission = "sys:user:getQueryPageUnitList")
    @GetMapping("/getQueryPageUnitList")
    public Result getQueryPageUnitList() {
        List<SysUnit> list = sysUserService.getQueryPageUnitList();
        return Result.success(list);
    }

    /**
     * 用户管理分页查询
     *
     * @param sysUserPageQueryFo
     * @return
     */
    @ReqApi(value = "用户管理分页查询", permission = "sys:user:queryPage")
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody SysUserQueryFo sysUserPageQueryFo) {
        Page<SysUserVo> page = sysUserService.queryPage(sysUserPageQueryFo);
        return Result.success(page);
    }

}
