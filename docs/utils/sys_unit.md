---
outline: deep
---

# SysUnit 单位

> 操作与单位相关的接口

以下接口说明来源于项目根目录下 [SysUnitController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysUnitController.java) 文件，可自行查看详情


## 获取前端统一组件的单位信息

### 请求说明

根据单位id编号获取前端统一组件的单位信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_unit/getComUnitInfo`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| unitId | 是 | `Integer` | 单位的id编号 |


### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| unitList | 是 | `List<SysUnit>` | 单位集合 |
| -id | 是 | `Integer` | 主键id编号 |
| -pid | 是 | `Integer` | 父级id编号 |
| -unitName | 是 | `String` | 单位名称 |
| -sortNum | 是 | `sortNum` | 序号 |
| -type | 是 | `SysUnitTypeEnum` | 类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_unit/enums/SysUnitTypeEnum.java#L22) 查看详细内容 |
| -ddType | 是 | `SysUnitDDTypeEnum` | 钉钉类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_unit/enums/SysUnitDDTypeEnum.java#L20) 查看详细内容 |
| -organizationCode | 是 | `String` | 组织code |
| -unifiedSocialCreditCode | 是 | `String` | 统一社会信用代码 |
| -isLast | 是 | `Boolean` | 是否最后 |
| -unitCode | 是 | `String` | 单位编码 |
| -deleted | 是 | `Integer` | 逻辑删除 |

## 获取单位区域树集合

### 请求说明

获取所有的单位区域树集合

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_unit/getAreaTree`

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
