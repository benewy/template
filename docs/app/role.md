---
outline: deep
---

# 权限配置

::: tip 提示
项目中使用了自定义的权限校验方式，只需在接口上加上`@ReqApi`，并赋予接口访问的权限值即可完成接入权限校验功能
:::

相关文件存在于项目根目录下 [AppWebConfig](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/java/com/beneway/core/config/AppWebConfig.java)、[ReqApi](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/annotation/ReqApi.java)、[LoginUserServiceImplements](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/interceptor/service/implement/LoginUserServiceImplements.java)

## 对象注册

权限校验检查对象配置

```java
package com.beneway.core.config;

import com.beneway.web.interceptor.service.LoginUserService;
import com.beneway.web.interceptor.service.implement.LoginUserServiceImplements;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/11 18:10
 */
@Configuration
public class AppWebConfig {

    // 将权限校验检查对象注册并交于IOC容器进行管理
    @Bean
    public LoginUserService getLoginUserService() {
        return new LoginUserServiceImplements();
    }

}
```

## 权限注解（`@ReqApi`）

> 该注解主要用于校验用户是否拥有特定权限操纵系统资源

```java
package com.beneway.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqApi {

  /**
   * 接口说明
   * @return
   */
  String value();

  /**
   * 权限key
   */
  String permission() default "";

}
```

## 权限校验

> 通过接口上的`@ReqApi`设定的权限，并通过检查用户是否拥有该权限

```java
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

    // 该方法用于检查用户是否拥有特定的权限
    @Override
    public Result checkUser(String userId, ReqApi reqApi) {
        // 获取用户信息
        LoginUserInfo loginUserInfo = sysUserService.getLoginUserInfo(userId);
        LoginUserUtils.setLoginUserInfo(loginUserInfo);

        if (!LoginUserUtils.isAdmin()) {
            // 不是admin用户进行权限判断
            if (reqApi != null) {
                String permission = reqApi.permission();
                if (StrUtil.isNotEmpty(permission)) {
                    // 对用户的权限列表进行检查
                    boolean b = sysUserService.isLoginUserPermission(permission);
                    // 若没有此权限，将拒绝访问系统资源
                    if (!b) {
                        String message = reqApi.value() + "," + permission + ",无该权限";
                        return Result.internalServerError(message);
                    }
                }
            }
        }

        return Result.success();
    }
}
```
