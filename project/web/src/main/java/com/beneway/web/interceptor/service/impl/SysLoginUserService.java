package com.beneway.web.interceptor.service.impl;

import cn.hutool.core.util.StrUtil;
import com.beneway.basic.common.result.Result;
import com.beneway.basic.common.result.ResultCodeEnum;
import com.beneway.basic.common.utils.login_user.LoginUserUtils;
import com.beneway.basic.common.system.entity.sys_user.vo.LoginUserInfo;
import com.beneway.basic.common.system.service.sys_user.SysUserService;
import com.beneway.basic.common.annotation.ReqApi;
import com.beneway.web.interceptor.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/11 17:44
 */
public class SysLoginUserService implements LoginUserService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result checkUser(String userId, ReqApi reqApi) {
        // 获取用户信息
        LoginUserInfo loginUserInfo = sysUserService.getLoginUserInfo(userId);
        LoginUserUtils.setLoginUserInfo(loginUserInfo);

        if (!LoginUserUtils.isAdmin()) {
            // 不是admin用户进行权限判断
            if (reqApi != null){
                String permission = reqApi.permission();
                if (StrUtil.isNotEmpty(permission)){
                    boolean b = sysUserService.isLoginUserPermission(permission);
                    if (!b){
                        String message = reqApi.value() + "," + permission + ",无该权限";
                        return Result.error(ResultCodeEnum.ERROR_PERMISSION, message);
                    }
                }
            }
        }

        return Result.ok();
    }

    @Override
    public String getUsername() {
        LoginUserInfo loginUserInfo = LoginUserUtils.getLoginUserInfo();
        if (loginUserInfo != null) {
            return loginUserInfo.getUsername();
        } else {
            return null;
        }
    }

}
