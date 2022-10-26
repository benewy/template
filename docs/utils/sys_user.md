---
outline: deep
---

# SysUser 用户资源

> 操作用户资源数据，获取用户信息，切换单位等

以下接口说明来源于项目根目录下 [SysUserController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysUserController.java) 文件，可自行查看详情

## 获取用户名称

### 请求说明

通过用户id编号获取用户名称

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_user/getComUserInfo`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| userId | 是 | `String` | 用户的id编号 |


### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| username | 是 | `String` | 用户名信息 |

## 获取前端统一组件的用户信息

### 请求说明

根据多个用户的id编号获取用户的详细信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_user/getComUserInfoList`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| userIds | 是 | `String` | 多个用户的id编号，以`,`分隔 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 用户id编号 |
| username | 是 | `String` | 用户名 |
| account | 是 | `String` | 用户账号 |
| createTime | 是 | `Date` | 创建时间 |
| employeeCode | 是 | `String` | 钉钉员工code |
| zwddAccountId | 是 | `String` | 钉钉用户id |
| currentUnitIndex | 是 | `Integer` | 用户当前单位索引 |
| deleted | 是 | `Integer` | 逻辑删除 |

## 获取登录用户信息

### 请求说明

获取登录用户的详细信息

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_user/getLoginUserInfo`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 用户id编号 |
| username | 是 | `String` | 用户名 |
| account | 是 | `String` | 用户账号 |
| createTime | 是 | `Date` | 创建时间 |
| employeeCode | 是 | `String` | 钉钉员工code |
| zwddAccountId | 是 | `String` | 钉钉用户id |
| currentUnitIndex | 是 | `Integer` | 用户当前单位索引 |
| deleted | 是 | `Integer` | 逻辑删除 |
| unitIdList | 是 | `List<Integer>` | 单位id列表 |
| currentUnitInList | 是 | `List<SysUnit>` | 当前单位线单位列表 |
| currentUnitId | 是 | `Integer` | 当前单位id |
| tagIdList | 是 | `List<Integer>` | 用户标签id列表 |

## 切换用户当前单位

### 请求说明

切换当前用户的所属单位

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/sys_user/switchCurrentUnit`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| unitId | 是 | `Integer` | 单位id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |
