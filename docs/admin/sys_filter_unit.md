---
outline: deep
---

# AdminSysFilterUnit 单位筛选

> 对单位过滤数据进行管理

以下接口说明来源于项目根目录下 [AdminSysFilterUnitController](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/java/com/beneway/core/controller/AdminSysFilterUnitController.java) 文件，可自行查看详情

## 获取单位过滤配置列表

### 请求说明

该接口所需权限:

permission = `sys:filterUnit:getList`

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/admin_sys_filter_unit/getList`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 否 | `String` | 主键id编号 |
| key | 是 | `SysFilterUnitKeyEnum` | 业务key，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_filter_unit/enums/SysFilterUnitKeyEnum.java#L23) 查看详细内容 |
| remark | 是 | `String` | 说明 |
| unitMode | 是 | `SysFilterUnitModeEnum` | 单位模式，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_filter_unit/enums/SysFilterUnitModeEnum.java#L23) 查看详细内容 |
| unitData | 是 | `String` | 单位数据 |
| unitModeDesc | 是 | `String` | 单位模式描述 |
| unitIdList | 是 | `List<Integer>` | 单位id列表 |
| tagList | 是 | `List<Integer>` | 标签id列表 |

## 单位过滤配置修改

### 请求说明

该接口所需权限:

permission = `sys:filterUnit:edit`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/admin_sys_filter_unit/edit`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 主键id编号 |
| key | 是 | `SysFilterUnitKeyEnum` | 业务key，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_filter_unit/enums/SysFilterUnitKeyEnum.java#L23) 查看详细内容 |
| remark | 是 | `String` | 说明 |
| unitMode | 是 | `SysFilterUnitModeEnum` | 单位模式，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_filter_unit/enums/SysFilterUnitModeEnum.java#L23) 查看详细内容 |
| unitData | 是 | `String` | 单位数据 |
| idList | 是 | `List<Integer>` | 单位数据id列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |
