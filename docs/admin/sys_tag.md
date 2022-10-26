---
outline: deep
---

# AdminSysTag 标签

> 标签管理，对标签进行操作

以下接口说明来源于项目根目录下 [AdminSysTagController](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/java/com/beneway/core/controller/AdminSysTagController.java) 文件，可自行查看详情

## 标签新增

### 请求说明

该接口所需权限：

permission = `sys:tag:add`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_tag/add`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| tagType | 是 | `SysTagTypeEnum` | 标签分类，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_tag/enums/SysTagTypeEnum.java#L6) 查看详细内容 |
| tagName | 是 | `String` | 标签名称 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 编辑标签

### 请求说明

该接口所需权限：

permission = `sys:tag:edit`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/admin_sys_tag/edit`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 标签id编号 |
| tagType | 是 | `SysTagTypeEnum` | 标签分类，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_tag/enums/SysTagTypeEnum.java#L6) 查看详细内容 |
| tagName | 是 | `String` | 标签名称 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 标签删除

### 请求说明

该接口所需权限：

permission = `sys:tag:del`

### 请求路径

HTTP方法： `DELETE`

请求URL：`http://localhost:8080/lists/admin_sys_tag/del`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 标签id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 根据类型获取标签列表

### 请求说明

根据不同的类型获取标签列表

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/admin_sys_tag/getListByType`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| type | 是 | `SysTagTypeEnum` | 标签分类，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_tag/enums/SysTagTypeEnum.java#L6) 查看详细内容 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 标签id编号 |
| tagType | 是 | `SysTagTypeEnum` | 标签分类，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_tag/enums/SysTagTypeEnum.java#L6) 查看详细内容 |
| tagName | 是 | `String` | 标签名称 |

## 标签管理分页查询

### 请求说明

该接口所需权限：

permission = `sys:tag:queryPage`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_tag/queryPage`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| page | 否 | `int` | 分页页码 |
| size | 否 | `int` | 每页条目数 |
| isMax | 否 | `int` | 是否限定`size`属性值，为`true`时则`size`值失效，`size`=2<sup>31</sup>-1，`false`则使用传入值 |
| id | 否 | `Integer` | 标签id编号 |
| type | 否 | `String` | 标签类型 |
| remake | 否 | `String` | 标签说明 |

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
| -id | 是 | `Integer` | 标签id编号 |
| -tagType | 是 | `SysTagTypeEnum` | 标签分类，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_tag/enums/SysTagTypeEnum.java#L6) 查看详细内容 |
| -tagName | 是 | `String` | 标签名称 |
