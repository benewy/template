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
