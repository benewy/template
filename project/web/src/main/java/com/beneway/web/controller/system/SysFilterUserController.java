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

import com.beneway.basic.system.sys_filter_user.enums.SysFilterUserKeyEnum;
import com.beneway.basic.system.sys_filter_user.po.SysFilterUser;
import com.beneway.basic.system.sys_filter_user.service.SysFilterUserService;
import com.beneway.basic.system.sys_unit.entity.vo.SysUnitVo;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.restful.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by zhy on 2022/3/11 11:35
 */
@RestController
@RequestMapping("/sys_filter_user")
public class SysFilterUserController {

    @Resource
    private SysFilterUserService sysFilterUserService;

    /**
     * 根据key获取配置信息
     * @param key
     * @return
     */
    @GetMapping("/getByKey")
    public Result getByKey(@RequestParam("key") String key) {
        SysFilterUserKeyEnum keyEnum = SysFilterUserKeyEnum.getByKey(key);
        SysFilterUser sysFilterUser = sysFilterUserService.getByKey(keyEnum);
        return Result.success(sysFilterUser);
    }

    /**
     * 获取前端选择用户的单位信息数据
     * @param key
     * @return
     */
    @GetMapping("/getUnitData")
    public Result getUnitData(@RequestParam String key) {
        SysFilterUserKeyEnum keyEnum = SysFilterUserKeyEnum.getByKey(key);
        List<SysUnitVo> unitVoList = sysFilterUserService.getUnitData(keyEnum);
        return Result.success(unitVoList);
    }

    /**
     * 获取前端选择用户的用户信息数据
     * @param key
     * @param unitId
     * @return
     */
    @GetMapping("/getUserData")
    public Result getUserData(@RequestParam String key, Integer unitId) {
        SysFilterUserKeyEnum keyEnum = SysFilterUserKeyEnum.getByKey(key);
        List<SysUser> userData = sysFilterUserService.getUserData(keyEnum, unitId);
        return Result.success(userData);
    }

    /**
     * 根据用户id串获取用户信息
     * @param userIds
     * @return
     */
    @GetMapping("/getUserList")
    public Result getUserList(@RequestParam String userIds) {
        List<SysUser> userList = sysFilterUserService.getUserList(userIds);
        return Result.success(userList);
    }

}
