---
outline: deep
---

# SysRole 角色

> 操作用户的角色关系

以下接口说明来源于项目根目录下 [SysRoleController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysRoleController.java) 文件，可自行查看详情


## 获取前端公共回显组件信息

### 请求说明

根据角色id编号获取前端公共回显组件信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_role/getComRoleInfo`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 角色id编号 |


### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 角色id编号 |
| title | 是 | `String` | 角色标题 |
| remark | 是 | `String` | 角色说明 |
| type | 是 | `SysRoleTypeEnum` | 角色类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_role/enums/SysRoleTypeEnum.java#L23) 查看详细内容 |
| unitTags | 是 | `String` | 类型为范围是的单位标签 |
| userTags | 是 | `String` | 类型为范围是的用户标签 |
| deleted | 是 | `String` | 逻辑删除 |
