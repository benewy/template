package com.beneway.web.interceptor.service.implement;

import cn.hutool.core.util.StrUtil;
import com.beneway.basic.system.sys_user.entity.vo.LoginUserInfo;
import com.beneway.basic.system.sys_user.service.SysUserService;
import com.beneway.basic.utils.login_user.LoginUserUtils;
import com.beneway.web.annotation.ReqApi;
import com.beneway.web.interceptor.service.LoginUserService;
import com.restful.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

public class LoginUserServiceImplements implements LoginUserService {

  @Resource
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
            return Result.internalServerError(message);
          }
        }
      }
    }

    return Result.success();
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
