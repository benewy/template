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

package com.beneway.basic.utils;

import com.beneway.basic.config.AppConfig;
import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.exception.RRException;
import com.beneway.basic.utils.login_user.LoginUserUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AppUtils {

  @Resource
  private AppConfig appConfig;

  /**
   * 根据当前登录用户id
   * @return
   */
  public String getLoginUserId() {
    UserTypeEnum userType = getLoginUserType();
    if (UserTypeEnum.SYSTEM.equals(userType)) {
      return LoginUserUtils.getLoginUserId();
    } else {
      throw new RRException("类型错误");
    }
  }

  /**
   * 获取当前登录用户类型
   * @return
   */
  public UserTypeEnum getLoginUserType() {
    String moduleType = getModuleType();
    if ("admin".equals(moduleType) || "dd".equals(moduleType)) {
      return UserTypeEnum.SYSTEM;
    } else {
      throw new RRException("类型错误");
    }
  }

  /**
   * 获取模块类型
   * @return
   */
  public String getModuleType() {
    return appConfig.getModuleType();
  }

  public Boolean isProd(){
    return appConfig.getActive().equals("prod");
  }

}
