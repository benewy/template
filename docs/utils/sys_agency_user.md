---
outline: deep
---

# SysAgencyUser 部门用户

> 操作部门和用户关系的接口

以下接口说明来源于项目根目录下 [SysAgencyUserController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysAgencyUserController.java) 文件，可自行查看详情

## 查询单个部门用户信息

### 请求说明

查询单个部门与用户的关系信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_agency_user/`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 主键id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 主键id编号 |
| agencyId | 是 | `String` | 部门id编号 |
| userId | 是 | `String` | 用户id编号 |

## 分页获取部门和用户关系的信息

### 请求说明

分页查询部门用户关系信息

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/sys_agency_user/queryPage`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| page | 否 | `int` | 分页页码 |
| size | 否 | `int` | 每页条目数 |
| isMax | 否 | `int` | 是否限定`size`属性值，为`true`时则`size`值失效，`size`=2<sup>31</sup>-1，`false`则使用传入值 |

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
| -id | 是 | `String` | 主键id编号 |
| -agencyId | 是 | `String` | 部门id编号 |
| -userId | 是 | `String` | 用户id编号 |

## 添加单个部门用户关系

### 请求说明

新添加一条部门用户关系信息

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/sys_agency_user/`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| agencyId | 是 | `String` | 部门id编号 |
| userId | 是 | `String` | 用户id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 修改部门用户关系信息

### 请求说明

修改部门和用户的关系信息

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/sys_agency_user/`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 主键id编号 |
| agencyId | 是 | `String` | 部门id编号 |
| userId | 是 | `String` | 用户id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 删除指定的部门用户关系记录

### 请求说明

根据指定的id编号进行删除

### 请求路径

HTTP方法： `DELETE`

请求URL：`http://localhost:8080/lists/sys_agency_user/`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 主键id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |
