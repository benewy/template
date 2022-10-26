---
outline: deep
---

# SysFilterUnit 单位筛选

> 用于对单位的筛选配置，获取到不同的公共组件数据等

以下接口说明来源于项目根目录下 [SysFilterUnitController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysFilterUnitController.java) 文件，可自行查看详情


## 获取前端选择单位公共组件数据

### 请求说明

获取前端选择单位公共组件数据

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_filter_unit/getSelectUnitData`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| key | 是 | `String` | 单位公共组件的key |


### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| sysUnitTree | 是 | `List<SysUnitVo>` | 区域选择树 |
| sysUnitList | 是 | `List<SysUnitVo>` | 单位列表 |

## 获取子集列表

### 请求说明

通过父级id编号获取所有子集

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_filter_unit/getChildren`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| pid | 是 | `Integer` | 父级id编号 |
| include | 否 | `boolean` | 是否包含父级 |

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

## 根据id获取一直到第一级的单位列表

### 请求说明

根据id编号获取，一直到第一级的单位列表信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_filter_unit/getTopLineList`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 单位id编号 |

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
