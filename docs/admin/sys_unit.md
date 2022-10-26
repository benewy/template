---
outline: deep
---

# AdminSysUnit 单位

> 单位管理，对单位信息进行管理操作

以下接口说明来源于项目根目录下 [AdminSysUnitController](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/java/com/beneway/core/controller/AdminSysUnitController.java) 文件，可自行查看详情

## 新增单位

### 请求说明

该接口所需权限：

permission = `sys:unit:add`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/admin_sys_unit/add`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| pid | 是 | `Integer` | 父级id编号 |
| unitName | 是 | `String` | 单位名称 |
| sortNum | 是 | `sortNum` | 序号 |
| type | 是 | `SysUnitTypeEnum` | 类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_unit/enums/SysUnitTypeEnum.java#L22) 查看详细内容 |
| ddType | 否 | `SysUnitDDTypeEnum` | 钉钉类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_unit/enums/SysUnitDDTypeEnum.java#L20) 查看详细内容 |
| organizationCode | 否 | `String` | 组织code |
| unifiedSocialCreditCode | 否 | `String` | 统一社会信用代码 |
| isLast | 否 | `Boolean` | 是否最后 |
| unitCode | 是 | `String` | 单位编码 |
| tagList | 是 | `List<Integer>` | 标签id列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 编辑单位

### 请求说明

该接口所需权限：

permission = `sys:unit:edit`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/admin_sys_unit/edit`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 单位id主键 |
| pid | 否 | `Integer` | 父级id编号 |
| unitName | 否 | `String` | 单位名称 |
| sortNum | 否 | `sortNum` | 序号 |
| type | 否 | `SysUnitTypeEnum` | 类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_unit/enums/SysUnitTypeEnum.java#L22) 查看详细内容 |
| ddType | 否 | `SysUnitDDTypeEnum` | 钉钉类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_unit/enums/SysUnitDDTypeEnum.java#L20) 查看详细内容 |
| organizationCode | 否 | `String` | 组织code |
| unifiedSocialCreditCode | 否 | `String` | 统一社会信用代码 |
| isLast | 否 | `Boolean` | 是否最后 |
| unitCode | 否 | `String` | 单位编码 |
| tagList | 是 | `List<Integer>` | 标签id列表 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 单位删除

### 请求说明

该接口所需权限：

permission = `sys:unit:del`

### 请求路径

HTTP方法： `DELETE`

请求URL：`http://localhost:8080/lists/admin_sys_unit/del`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 单位id主键 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 获取单位列表

### 请求说明

该接口所需权限：

permission = `sys:unit:getTreeList`

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/admin_sys_unit/getTreeList`

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
| children | 是 | `List<SysUnitVo>` | 子集 |
| parentList | 是 | `List<SysUnit>` | 父级列表 |
| tagList | 是 | `List<Integer>` | 标签 |
