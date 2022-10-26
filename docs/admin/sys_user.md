---
outline: deep
---

# AdminSysUser 用户

> 用户管理，管理用户的登录退出，信息添加修改等

以下接口说明来源于项目根目录下 [AdminSysUserController](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/java/com/beneway/core/controller/AdminSysUserController.java) 文件，可自行查看详情

## 浙政钉扫码登录

### 请求说明

使用浙政钉方式登录

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_user/qrLogin`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| code | 是 | `String` | 扫码凭证 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| username | 是 | `String` | 用户名 |
| account | 是 | `String` | 账号 |
| token | 是 | `String` | token凭证 |

## 常规登陆

### 请求说明

使用账号密码登录

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_user/login`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| account | 是 | `String` | 账号 |
| password | 是 | `String` | 密码 |
| verifyCode | 是 | `String` | 验证码 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| username | 是 | `String` | 用户名 |
| account | 是 | `String` | 账号 |
| token | 是 | `String` | token凭证 |

## 退出登录

### 请求说明

用户退出登录状态

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/admin_sys_user/logout`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 用户新增

### 请求说明

该接口所需权限：

permission = `sys:user:add`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_user/add`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| username | 是 | `String` | 用户名 |
| account | 是 | `String` | 用户账号 |
| password | 是 | `String` | 用户密码 |
| employeeCode | 否 | `String` | 钉钉员工code |
| zwddAccountId | 否 | `String` | 钉钉用户id |
| currentUnitIndex | 否 | `Integer` | 用户当前单位索引 |
| roleList | 是 | `List<Integer>` | 角色列表 |
| tagList | 是 | `List<Integer>` | 标签列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 用户编辑

### 请求说明

该接口所需权限：

permission = `sys:user:edit`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/admin_sys_user/edit`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 用户id编号 |
| username | 否 | `String` | 用户名 |
| account | 否 | `String` | 用户账号 |
| password | 否 | `String` | 用户密码 |
| employeeCode | 否 | `String` | 钉钉员工code |
| zwddAccountId | 否 | `String` | 钉钉用户id |
| currentUnitIndex | 否 | `Integer` | 用户当前单位索引 |
| roleList | 否 | `List<Integer>` | 角色列表 |
| tagList | 否 | `List<Integer>` | 标签列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 用户删除

### 请求说明

该接口所需权限：

permission = `sys:user:del`

### 请求路径

HTTP方法： `DELETE`

请求URL：`http://localhost:8080/lists/admin_sys_user/del`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| userId | 是 | `String` | 用户id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 获取用户管理分页查询单位列表

### 请求说明

该接口所需权限：

permission = `sys:user:getQueryPageUnitList`

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/admin_sys_user/getQueryPageUnitList`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 主键id编号 |
| pid | 是 | `Integer` | 父级id编号 |
| unitName | 是 | `String` | 单位名称 |
| sortNum | 是 | `sortNum` | 序号 |
| type | 是 | `SysUnitTypeEnum` | 类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_unit/enums/SysUnitTypeEnum.java#L22) 查看详细内容 |
| ddType | 是 | `SysUnitDDTypeEnum` | 钉钉类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_unit/enums/SysUnitDDTypeEnum.java#L20) 查看详细内容 |
| organizationCode | 是 | `String` | 组织code |
| unifiedSocialCreditCode | 是 | `String` | 统一社会信用代码 |
| isLast | 是 | `Boolean` | 是否最后 |
| unitCode | 是 | `String` | 单位编码 |
| deleted | 是 | `Integer` | 逻辑删除 |

## 用户管理分页查询

### 请求说明

该接口所需权限：

permission = `sys:user:queryPage`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_user/queryPage`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| page | 否 | `int` | 分页页码 |
| size | 否 | `int` | 每页条目数 |
| isMax | 否 | `int` | 是否限定`size`属性值，为`true`时则`size`值失效，`size`=2<sup>31</sup>-1，`false`则使用传入值 |
| username | 否 | `String` | 用户名 |
| unitId | 是 | `Integer` | 单位id |
| unitIdList | 是 | `Collection<Integer>` | 单位列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| total | 是 | `long` | 记录的总数 |
| size | 是 | `long` | 每页显示条数 |
| current | 是 | `long` | 当前页 |
| orders | 是 | `List<OrderItem>` | 排序字段信息 |
| optimizeCountSql | 是 | `boolean` | 是否自动优化 COUNT SQL |
| searchCount | 是 | `boolean` | 是否进行 count 查询，设置false后不会返回total |
| optimizeJoinOfCountSql | 是 | `boolean` | 是否自动优化JOIN的 COUNT SQL |
| countId | 是 | `String` | - |
| maxLimit | 是 | `Long` | 单页分页条数限制 |
| records | 是 | `List<T>` | 存放查询出来的数据 |
| -id | 是 | `String` | 用户id编号 |
| -username | 是 | `String` | 用户名 |
| -account | 是 | `String` | 用户账号 |
| -createTime | 是 | `Date` | 创建时间 |
| -employeeCode | 是 | `String` | 钉钉员工code |
| -zwddAccountId | 是 | `String` | 钉钉用户id |
| -currentUnitIndex | 是 | `Integer` | 用户当前单位索引 |
| -deleted | 是 | `Integer` | 逻辑删除 |
| -unitList | 是 | `List<Integer>` | 单位列表 |
| -roleList | 是 | `List<Integer>` | 角色列表 |
| -tagList | 是 | `List<Integer>` | 标签列表 |
