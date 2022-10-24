---
outline: deep
---

# ip地址工具

> 对网络请求中的ip地址进行操作的工具类

## IPUtil

> 获取客户端的ip地址

### Usage

示例：

```java
  public static void main(String[] args) {
    ...
    // 获取客户端的真实ip地址
     String ip = IPUtil.getIpAddr();
    ...
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [getIpAddr](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/IPUtil.java#L24) | `String` | - | 获取客户端的真实ip地址 |
| [getIpAddr](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/irs/CreditDataUtils.java#L32) | `String` | request(`HttpServletRequest`) | 获取客户端的真实ip地址 |
