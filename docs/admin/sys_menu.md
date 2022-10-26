---
outline: deep
---

# AdminSysMenu 菜单

> 管理菜单，根据用户的角色不同给与不同菜单列表

以下接口说明来源于项目根目录下 [AdminSysMenuController](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/java/com/beneway/core/controller/AdminSysMenuController.java) 文件，可自行查看详情

## 新增菜单

### 请求说明

该接口所需权限:

permission = `sys:menu:add`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_menu/add`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| pid | 是 | `Integer` | 父级菜单 |
| title | 是 | `Integer` | 标题 |
| icon | 否 | `String` | 图标 |
| path | 是 | `String` | 路径 |
| type | 是 | `Integer` | 类型 0：菜单夹 1：菜单 |
| isNewTab | 否 | `Boolean` | 是否在新标签下打开 |
| sortNum | 否 | `Float` | 排序 |
| permissionList | 是 | `List<String>` | 权限列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 编辑菜单

### 请求说明

该接口所需权限:

permission = `sys:menu:edit`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/admin_sys_menu/edit`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 主键id编号 |
| pid | 否 | `Integer` | 父级菜单 |
| title | 否 | `Integer` | 标题 |
| icon | 否 | `String` | 图标 |
| path | 否 | `String` | 路径 |
| type | 否 | `Integer` | 类型 0：菜单夹 1：菜单 |
| isNewTab | 否 | `Boolean` | 是否在新标签下打开 |
| sortNum | 否 | `Float` | 排序 |
| permissionList | 否 | `List<String>` | 权限列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 删除菜单

### 请求说明

该接口所需权限:

permission = `sys:menu:del`

### 请求路径

HTTP方法： `DELETE`

请求URL：`http://localhost:8080/lists/admin_sys_menu/del`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 菜单删除id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 获取菜单树列表

### 请求说明

该接口所需权限：

permission = `sys:menu:getTreeList`

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/admin_sys_menu/getTreeList`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 主键id编号 |
| pid | 否 | `Integer` | 父级菜单 |
| title | 否 | `Integer` | 标题 |
| icon | 否 | `String` | 图标 |
| path | 否 | `String` | 路径 |
| type | 否 | `Integer` | 类型 0：菜单夹 1：菜单 |
| isNewTab | 否 | `Boolean` | 是否在新标签下打开 |
| sortNum | 否 | `Float` | 排序 |
| children | 否 | `List<SysMenuVo>` | 子菜单 |
| permissionList | 否 | `List<String>` | 权限列表 |

## 获取登录用户菜单

### 请求说明

**该接口无请求参数**

**该接口无需任何权限**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/admin_sys_menu/getUserMenuList`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 主键id编号 |
| pid | 否 | `Integer` | 父级菜单 |
| title | 否 | `Integer` | 标题 |
| icon | 否 | `String` | 图标 |
| path | 否 | `String` | 路径 |
| type | 否 | `Integer` | 类型 0：菜单夹 1：菜单 |
| isNewTab | 否 | `Boolean` | 是否在新标签下打开 |
| sortNum | 否 | `Float` | 排序 |
| children | 否 | `List<SysMenuVo>` | 子菜单 |
| permissionList | 否 | `List<String>` | 权限列表 |
