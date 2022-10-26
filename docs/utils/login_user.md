---
outline: deep
---

# 登录用户工具

> 用于对以登录的用户进行资源获取或操作

## LoginUserUtils

> 对登录的用户信息进行操作

### Usage

示例：

```java
  public String getLoginUserId() {
    ...
    return LoginUserUtils.getLoginUserId();
  }

```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [getRequest](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/login_user/LoginUserUtils.java#L38) | `HttpServletRequest` | - | 获取当前的`HttpServletRequest`对象 |
| [setLoginUserInfo](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/login_user/LoginUserUtils.java#L47) | - | loginUserInfo(`LoginUserInfo`) | 设置登陆用户信息 |
| [getLoginUserInfo](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/login_user/LoginUserUtils.java#L57) | `LoginUserInfo` | - | 获取登录用户信息 |
| [getLoginUserId](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/login_user/LoginUserUtils.java#L67) | `String` | - | 获取登录人员id |
| [isAdmin](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/login_user/LoginUserUtils.java#L76) | `boolean` | - | 判断当前用户是否是admin账户 |
| [getCurrentUnitOfType](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/login_user/LoginUserUtils.java#L86) | `SysUnit` | - | 根据类型获取当前单位id |
| [getCurrentUnitIdOfType](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/login_user/LoginUserUtils.java#L103) | `Integer` | typeEnum(`SysUnitTypeEnum`) | 根据类型获取当前单位id |
| [getCurrentAgencyId](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/login_user/LoginUserUtils.java#L112) | `Integer` | - | 获取当前单位id |
| [getCurrentAreaId](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/login_user/LoginUserUtils.java#L120) | `Integer` | - | 获取当前区域id |
