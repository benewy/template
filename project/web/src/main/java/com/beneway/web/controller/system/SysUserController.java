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

package com.beneway.web.controller.system;

import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.beneway.basic.system.sys_user.entity.vo.LoginUserInfo;
import com.beneway.basic.system.sys_user.entity.vo.SysUserComVo;
import com.beneway.basic.system.sys_user.service.SysUserService;
import com.beneway.basic.utils.login_user.LoginUserUtils;
import com.restful.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by zhy on 2022/3/5 10:37
 */
@RestController()
@RequestMapping("/sys_user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 获取前端统一组件的用户信息
     * @return
     */
    @GetMapping("/getComUserInfo")
    public Result getComUserInfo(@RequestParam String userId) {
        SysUserComVo comUserInfo = sysUserService.getComUserInfo(userId);
        return Result.success(comUserInfo);
    }

    /**
     * 获取前端统一组件的用户信息
     * @return
     */
    @GetMapping("/getComUserInfoList")
    public Result getComUserInfoList(@RequestParam String userIds) {
        List<SysUser> vos = sysUserService.getComUserInfoList(userIds);
        return Result.success(vos);
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @GetMapping("/getLoginUserInfo")
    public Result getLoginUserInfo() {
        LoginUserInfo loginUserInfo = LoginUserUtils.getLoginUserInfo();
        return Result.success(loginUserInfo);
    }

    /**
     * 切换用户当前单位
     * @param unitId
     */
    @PutMapping("/switchCurrentUnit")
    public Result switchCurrentUnit(@RequestParam Integer unitId) {
        String userId = LoginUserUtils.getLoginUserId();
        sysUserService.switchCurrentUnit(userId, unitId);
        return Result.success();
    }

}
