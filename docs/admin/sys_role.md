---
outline: deep
---

# AdminSysRole 角色

> 角色管理，对角色列表进行操作

以下接口说明来源于项目根目录下 [AdminSysRoleController](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/java/com/beneway/core/controller/AdminSysRoleController.java) 文件，可自行查看详情

## 角色新增

### 请求说明

该接口所需权限：

permission = `sys:role:add`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_role/add`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| title | 是 | `String` | 角色标题 |
| remark | 否 | `String` | 角色说明 |
| type | 是 | `SysRoleTypeEnum` | 角色类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_role/enums/SysRoleTypeEnum.java#L23) 查看详细内容 |
| unitTags | 否 | `String` | 类型为范围是的单位标签 |
| userTags | 否 | `String` | 类型为范围是的用户标签 |
| SysMenuIdList | 是 | `List<Integer>` | 菜单id列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 角色编辑

### 请求说明

该接口所需权限：

permission = `sys:role:edit`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/admin_sys_role/edit`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 角色主键id编号 |
| title | 是 | `String` | 角色标题 |
| remark | 否 | `String` | 角色说明 |
| type | 是 | `SysRoleTypeEnum` | 角色类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_role/enums/SysRoleTypeEnum.java#L23) 查看详细内容 |
| unitTags | 否 | `String` | 类型为范围是的单位标签 |
| userTags | 否 | `String` | 类型为范围是的用户标签 |
| SysMenuIdList | 是 | `List<Integer>` | 菜单id列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 角色删除

### 请求说明

该接口所需权限：

permission = `sys:role:del`

### 请求路径

HTTP方法： `DELETE`

请求URL：`http://localhost:8080/lists/admin_sys_role/del`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 角色id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 角色管理分页查询

### 请求说明

该接口所需权限：

permission = `sys:role:queryPage`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_role/queryPage`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| page | 否 | `int` | 分页页码 |
| size | 否 | `int` | 每页条目数 |
| isMax | 否 | `int` | 是否限定`size`属性值，为`true`时则`size`值失效，`size`=2<sup>31</sup>-1，`false`则使用传入值 |
| title | 否 | `String` | 标题 |

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
| -id | 是 | `Integer` | 角色主键id编号 |
| -title | 是 | `String` | 角色标题 |
| -remark | 是 | `String` | 角色说明 |
| -type | 是 | `SysRoleTypeEnum` | 角色类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_role/enums/SysRoleTypeEnum.java#L23) 查看详细内容 |
| -unitTags | 是 | `String` | 类型为范围是的单位标签 |
| -userTags | 是 | `String` | 类型为范围是的用户标签 |
| -SysMenuIdList | 是 | `List<Integer>` | 菜单id列表 |

## 获取角色列表

### 请求说明

该接口所需权限：

permission = `sys:role:getList`

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/admin_sys_role/getList`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| type | 是 | `String` | 角色类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_role/enums/SysRoleTypeEnum.java#L23) 查看详细内容 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 角色主键id编号 |
| title | 是 | `String` | 角色标题 |
| remark | 是 | `String` | 角色说明 |
| type | 是 | `SysRoleTypeEnum` | 角色类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_role/enums/SysRoleTypeEnum.java#L23) 查看详细内容 |
| unitTags | 是 | `String` | 类型为范围是的单位标签 |
| userTags | 是 | `String` | 类型为范围是的用户标签 |
