---
outline: deep
---

# 政务钉钉工具

> 用于操作政务钉钉，与政务钉钉交互

## entity.msg

该包放置了政务钉钉的工作通知，`Card`卡片消息设置

如若需要修改可在项目根目录下 [entity.msg](https://github.com/elonehoo/benewy-template/tree/main/project/basic/src/main/java/com/beneway/basic/utils/dd/entity/msg) 包下修改 `Card` 或 `CardBtn` 实体

## ZWDDBaseUtils

> 政务钉钉的基础工具类

### Usage

示例：

```java
public JsonObject getUserInfo(String auth_code) {
    ...
    JsonObject data = ZWDDBaseUtils.getJsonData(content);
    ...
}

```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [getJsonSuccess](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDBaseUtils.java#L51) | `boolean` | json(`String`) | 获取`json`中的`content`参数，并返回获取状态 |
| [getJsonData](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDBaseUtils.java#L62) | `JsonObject` | json(`String`) | 将`String`类型的数据转换成`JsonObject` |
| [requestProxy](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDBaseUtils.java#L71) | `String` | requestProxyUrl(`String`)<br/>type(`String`)<br/>api(`String`)<br/>map(`Map<String, String>`) | 将请求进行代理 |
| [requestProxy](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDBaseUtils.java#L80) | `String` | executableClient(`ExecutableClient`)<br/>type(`String`)<br/>api(`String`)<br/>map(`Map<String, String>`) | 将请求进行代理 |
| [flushToken](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDBaseUtils.java#L114) | - | key(`String`)<br/>secret(`String`)<br/>executableClient(`ExecutableClient`)<br/>TOKEN_TYPE(`String`) | 刷新政务钉钉token凭证 |


## ZWDDPhoneUtils

> 用于手机政务钉钉的工具类

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [getExecutableClient](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDPhoneUtils.java#L66) | `ExecutableClient` | - | 获取一个新的ExecutableClient |
| [getToken](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDPhoneUtils.java#L84) | `String` | - | 获取数据库中存储的移动端的token凭证 |
| [flushToken](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDPhoneUtils.java#L90) | - | - | 刷新数据库中移动端token凭证 |
| [getUserInfo](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDPhoneUtils.java#L94) | `JsonObject` | auth_code(`String`) | 获取政务钉钉用户信息 |
| [sendWorkCardMsg](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDPhoneUtils.java#L125) | - | accountIdList(`List<String>`)<br/>card(`Card`) | 发送工作卡片通知 |

## ZWDDQrcodeUtils

> 用于政务钉钉二维码的工具类

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [getExecutableClient](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDQrcodeUtils.java#L56) | `ExecutableClient` | - | 获取一个新的ExecutableClient |
| [getToken](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDQrcodeUtils.java#L74) | `String` | - | 获取数据库中存储的二维码扫码的token凭证 |
| [flushToken](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDQrcodeUtils.java#L78) | - | - | 刷新数据库中二维码扫码的token凭证 |
| [getUserInfoQrcode](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/ZWDDQrcodeUtils.java#L88) | `JsonObject` | code(`String`) | 获取政务钉钉用户信息 |
