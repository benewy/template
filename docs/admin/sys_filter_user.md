---
outline: deep
---

# AdminSysFilterUser 用户筛选

> 对用户过滤数据进行管理

以下接口说明来源于项目根目录下 [AdminSysFilterUserController](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/java/com/beneway/core/controller/AdminSysFilterUserController.java) 文件，可自行查看详情

## 获取用户过滤配置列表

### 请求说明

该接口所需权限:

permission = `sys:filterUser:getList`

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/admin_sys_filter_user/getList`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 主键id编号 |
| key | 是 | `SysFilterUserKeyEnum` | 业务key |
| type | 是 | `SysFilterUserTypeEnum` | 类型，A-全部，U-用户，N-单位 |
| remark | 是 | `String` | 说明信息 |
| unitMode | 是 | `SysFilterUnitModeEnum` | 单位模式，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_filter_unit/enums/SysFilterUnitModeEnum.java#L23) 查看详细内容 |
| unitData | 是 | `String` | 单位数据 |
| userMode | 是 | `SysFilterUserModeEnum` | 用户模式，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_filter_user/enums/SysFilterUserModeEnum.java#L23) 查看详细内容 |
| userData | 是 | `String` | 用户数据 |
| unitDataList | 是 | `String` | 单位数据 |
| userDataList | 是 | `String` | 用户数据 |
| unitModeDesc | 是 | `String` | 单位模式描述 |
| userModeDesc | 是 | `String` | 用户模式描述 |

## 用户过滤配置修改

### 请求说明

该接口所需权限:

permission = `sys:filterUser:edit`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/admin_sys_filter_user/edit`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| key | 是 | `SysFilterUserKeyEnum` | 业务key |
| unitData | 是 | `String` | 单位数据 |
| userData | 是 | `String` | 用户数据 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |
