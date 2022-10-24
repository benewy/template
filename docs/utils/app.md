---
outline: deep
---

# 应用系统工具

> 用于对当前系统操作的工具

## AppUtils

> 主要用于操作系统中重要数据的工具

### Usage

示例：

```java
  @Resource
  private AppUtils appUtils;

  public void test() {
    ...
    // 获取当前登录的用户id
    String loginUserId = appUtils.getLoginUserId();
    ...
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [getLoginUserId](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/AppUtils.java#L35) | `String` | - | 获取当前登录的用户id编号 |
| [getLoginUserType](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/AppUtils.java#L48) | `UserTypeEnum` | - | 获取当前登录用户类型 |
| [getModuleType](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/AppUtils.java#L61) | `String` | - | 获取模块类型 |
| [isProd](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/AppUtils.java#L65) | `Boolean` | - | 判断当前环境是否是生产环境 |
