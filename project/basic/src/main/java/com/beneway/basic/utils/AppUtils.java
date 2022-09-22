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
    for (String active : appConfig.getActives()) {
      if ("prod".equals(active)){
        return true;
      }
    }
    return false;
  }

}
