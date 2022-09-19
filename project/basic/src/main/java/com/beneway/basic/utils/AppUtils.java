package com.beneway.basic.utils;

import com.beneway.basic.config.AppConfig;
import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.exception.RRException;
import org.springframework.beans.factory.annotation.Autowired;

public class AppUtils {

  @Autowired
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

  /**
   * 获取是否为正式环境
   * @return
   */
  public boolean isProd() {
    return "prod".equals(appConfig.getActive());
  }

}
