---
outline: deep
---

# 工具前言

::: warning 注意
全局工具中的工具并不是所有都必须要使用，可以根据实际情况进行使用
:::

## Usage

### 全局工具

::: tip 提示
项目中的常用工具类位于项目根目录下 [basic.utils](https://github.com/elonehoo/benewy-template/tree/main/project/basic/src/main/java/com/beneway/basic/utils)、 [web.utils](https://github.com/elonehoo/benewy-template/tree/main/project/web/src/main/java/com/beneway/web/utils)
:::

全局工具为项目系统中常用的工具类，可分为静态工具类和交由 Spring 容器管理的工具类

静态工具类：可直接类名调用工具类方法

Spring 管理的工具类：需要使用时，通过 `@Resource` 或 `@Autowired` 或 `new` 一个工具类对象，即可使用工具类中的方法

### 常用工具&Admin后台工具

常用工具及Admin后台工具为项目中能够使用的常用 `Service` 服务

::: tip 提示

其中所有的服务都以全部交由 Spring 容器管理，如若需要使用其中的服务，可自行注入使用。基础服务位于 [system](https://github.com/elonehoo/benewy-template/tree/main/project/basic/src/main/java/com/beneway/basic/system)、用户登录服务 [service.sys_user](https://github.com/elonehoo/benewy-template/tree/main/project/core/src/main/java/com/beneway/core/service/sys_user)

Admin后台服务位于 [Admin](https://github.com/elonehoo/benewy-template/tree/main/project/core/src/main/java/com/beneway/core/controller)

:::

示例

```java
    // 通过如下方法即可 SysUserService
    @Resource
    private SysUserService sysUserService;
```

**注意：**

除 [appConfig.excludePaths](/app/settings#application-yml) 配置的排除路径外，其余接口均需要携带`token`

::: tip 注意
在登陆成功之后，即可获得token凭证，Header中配置的token名称为`sa-token.token-name`，[查看详情](/app/settings#application-basic-yml)
:::

**token配置**

Header：

| 参数 | 说明 |
| --- | --- |
| access_token | 72c18da95552259dd7c4aaa52e9fa37f |

### 请求结果

::: tip 注意

后台使用了统一的返回结果集，返回的数据将在`body`参数中，具体内容请查看下方 **返回参数** 表

常用工具及Admin后台工具中的所有返回参数示例表的参数均已`body`为根节点开始

参数前携带`-`则为上一个参数的子参数

:::

**返回参数**

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| statusCodeValue | 是 | `int` | 请求状态码 |
| statusCode | 是 | `HttpStatus` | 请求状态 |
| body | 是 | `T` | 请求返回的数据集，返回结果为泛型 |
