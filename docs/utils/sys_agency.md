---
outline: deep
---

# SysAgency 部门

> 操作与部门相关的接口

以下接口说明来源于项目根目录下 [SysAgencyController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysAgencyController.java) 文件，可自行查看详情

## 查询单个部门

### 请求说明

该接口主要根据id编号进行查询并返回查询得到的`SysAgency`对象

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_agency/`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 部门id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 部门id编号 |
| name | 是 | `String` | 部门名称 |
| seq | 是 | `String` | 部门排序方式 |
| pid | 是 | `Integer` | 部门的父级id编号 |
| userIds | 是 | `String` | 部门中的所有用户的id编号 |
| managerIds | 是 | `String` | 部门的审核用户id编号 |
| isFather | 是 | `String` | 部门是否是父级单位 |


## 分页查询部门

### 请求说明

分页查询部门信息

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/sys_agency/queryPage`

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
| -id | 是 | `String` | 部门id编号 |
| -name | 是 | `String` | 部门名称 |
| -seq | 是 | `String` | 部门排序方式 |
| -pid | 是 | `Integer` | 部门的父级id编号 |
| -userIds | 是 | `String` | 部门中的所有用户的id编号 |
| -managerIds | 是 | `String` | 部门的审核用户id编号 |
| -isFather | 是 | `String` | 部门是否是父级单位 |
| -children | 是 | `List<SysAgencyVo>` | 子部门信息 |

## 获取单位列表

### 请求说明

获取所有的单位列表

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_agency/getTreeList`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 部门id编号 |
| name | 是 | `String` | 部门名称 |
| seq | 是 | `String` | 部门排序方式 |
| pid | 是 | `Integer` | 部门的父级id编号 |
| userIds | 是 | `String` | 部门中的所有用户的id编号 |
| managerIds | 是 | `String` | 部门的审核用户id编号 |
| isFather | 是 | `String` | 部门是否是父级单位 |
| children | 是 | `List<SysAgencyVo>` | 子部门信息 |

## 添加部门

### 请求说明

新添加一部门

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/sys_agency/`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| name | 是 | `String` | 部门名称 |
| seq | 否 | `String` | 部门排序方式 |
| pid | 否 | `Integer` | 部门的父级id编号 |
| userIds | 否 | `String` | 部门中的所有用户的id编号 |
| managerIds | 否 | `String` | 部门的审核用户id编号 |
| isFather | 否 | `String` | 部门是否是父级单位 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 修改单个部门信息

### 请求说明

对某一个具体的部门信息进行修改

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/sys_agency/`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 否 | `String` | 部门id编号 |
| name | 否 | `String` | 部门名称 |
| seq | 否 | `String` | 部门排序方式 |
| pid | 否 | `Integer` | 部门的父级id编号 |
| userIds | 否 | `String` | 部门中的所有用户的id编号 |
| managerIds | 否 | `String` | 部门的审核用户id编号 |
| isFather | 否 | `String` | 部门是否是父级单位 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 逻辑删除一个部门

### 请求说明

该接口将会逻辑删除一个指定id的部门

### 请求路径

HTTP方法： `DELETE`

请求URL：`http://localhost:8080/lists/sys_agency/`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 否 | `String` | 部门id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |
