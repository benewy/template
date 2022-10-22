---
outline: deep
---

# 工具前言

::: warning 注意
全局工具中的工具并不是所有都必须要使用，可以根据实际情况进行使用
:::

## 使用

### 全局工具

::: tip 提示
项目中的常用工具类位于项目根目录下 [basic.utils](https://github.com/elonehoo/benewy-template/tree/main/project/basic/src/main/java/com/beneway/basic/utils)、 [web.utils](https://github.com/elonehoo/benewy-template/tree/main/project/web/src/main/java/com/beneway/web/utils)
:::

全局工具为项目系统中常用的工具类，可分为静态工具类和交由 Spring 容器管理的工具类

静态工具类：可直接类名调用工具类方法

Spring 管理的工具类：需要使用时，通过 `@Resource` 或 `@Autowired` 或 `new` 一个工具类对象，即可使用工具类中的方法

### 常用工具

常用工具为项目中能够使用的常用 `Service` 服务

::: tip 提示
其中所有的服务都以全部交由 Spring 容器管理，如若需要使用其中的服务，可自行注入使用。基础服务位于 [system](https://github.com/elonehoo/benewy-template/tree/main/project/basic/src/main/java/com/beneway/basic/system)、用户登录服务 [service.sys_user](https://github.com/elonehoo/benewy-template/tree/main/project/core/src/main/java/com/beneway/core/service/sys_user)
:::

示例

```java
    // 通过如下方法即可 SysUserService
    @Resource
    private SysUserService sysUserService;
```
