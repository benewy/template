---
outline: deep
---

# SysFilterUser 用户筛选

> 根据不同的登录账号获取不同的配置信息、单位信息、个人信息等

以下接口说明来源于项目根目录下 [SysFilterUserController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysFilterUserController.java) 文件，可自行查看详情


## 根据key获取配置信息

### 请求说明

获取用户的配置信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_filter_user/getByKey`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| key | 是 | `String` | 用户配置的唯一值 |


### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 主键id编号 |
| key | 是 | `SysFilterUserKeyEnum` | 业务key |
| type | 是 | `SysFilterUserTypeEnum` | 类型，A-全部，U-用户，N-单位 |
| remark | 是 | `String` | 说明信息 |
| unitMode | 是 | `SysFilterUnitModeEnum` | 单位模式，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_filter_unit/enums/SysFilterUnitModeEnum.java#L23) 查看详细内容 |
| unitData | 是 | `String` | 单位数据 |
| userMode | 是 | `SysFilterUserModeEnum` | 用户模式，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_filter_user/enums/SysFilterUserModeEnum.java#L23) 查看详细内容 |
| userData | 是 | `String` | 用户数据 |

## 获取前端选择用户的单位信息数据

### 请求说明

获取当前登录所选角色的单位信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_filter_user/getUnitData`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| key | 是 | `String` | 用户配置的唯一值 |

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

## 获取前端选择用户的用户信息数据

### 请求说明

获取当前登录所选角色的用户信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_filter_user/getUserData`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| key | 是 | `String` | 用户配置的唯一值 |
| unitId | 是 | `Integer` | 单位的id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 主键id编号 |
| username | 是 | `String` | 用户名 |
| account | 是 | `String` | 账号 |
| createTime | 是 | `Date` | 创建时间 |
| employeeCode | 是 | `String` | 钉钉员工code |
| zwddAccountId | 是 | `String` | 钉钉用户id |
| currentUnitIndex | 是 | `Integer` | 用户当前单位索引 |
| deleted | 是 | `Integer` | 逻辑删除 |

## 根据用户id串获取用户信息

### 请求说明

根据多个用户的id编号获取多人的用户信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_filter_user/getUserList`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| userIds | 是 | `String` | 多个用户的id编号，以`,`分隔 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 主键id编号 |
| username | 是 | `String` | 用户名 |
| account | 是 | `String` | 账号 |
| createTime | 是 | `Date` | 创建时间 |
| employeeCode | 是 | `String` | 钉钉员工code |
| zwddAccountId | 是 | `String` | 钉钉用户id |
| currentUnitIndex | 是 | `Integer` | 用户当前单位索引 |
| deleted | 是 | `Integer` | 逻辑删除 |
