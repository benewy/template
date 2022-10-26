---
outline: deep
---

# SysMessage 消息

> 获取当前登录用户的消息列表及对消息的操作

以下接口说明来源于项目根目录下 [SysMessageController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysMessageController.java) 文件，可自行查看详情


## 分页获取当前用户消息列表

### 请求说明

以分页形式获取当前用户的消息列表

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/sys_message/queryPageMessage`

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
| -id | 是 | `String` | 主键id编号 |
| -problemId | 是 | `String` | 合同id |
| -lookUser | 是 | `String` | 查看人 |
| -userType | 是 | `UserTypeEnum` | 查看人用户类型，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/enums/UserTypeEnum.java#L27) 查看详细内容 |
| -content | 是 | `String` | 消息内容 |
| -createUser | 是 | `String` | 创建人(发送人) 默认为空 |
| -createTime | 是 | `Date` | 创建时间 |
| -lookTime | 是 | `Date` | 查看时间 |
| -title | 是 | `String` | 合同标题 |

## 设置已读

### 请求说明

将已选择的消息设置成已读状态

请求体格式化：Content-Type为`application/json`，通过`json`格式化请求体。

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/sys_message/isRead`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| ids | 是 | `List<String>` | 需要设置成已读的的消息id集合 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 全部已读

### 请求说明

将所有消息设置成已读状态

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_message/isReadAll`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 删除已读信息

### 请求说明

将已读的消息进行删除操作

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_message/clear`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 剩余未读信息数

### 请求说明

获取剩余未读消息的数量

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_message/messageUnread`

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `Integer` | 剩余未读消息的数量 |
