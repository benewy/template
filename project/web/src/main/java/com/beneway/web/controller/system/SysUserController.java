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
