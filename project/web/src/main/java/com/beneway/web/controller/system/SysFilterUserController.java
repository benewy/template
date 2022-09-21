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
