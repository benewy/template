---
outline: deep
---

# IRS信用档案工具

> 对企业或部门的信用档案操作的工具类

## CreditDataUtils

> 查询IRS信息

### Usage

示例：

```java
  public static void main(String[] args) {
    ...
    // 查询企业信用报告
    String query = query("91330000142944445H");
    ...
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [query](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/irs/CreditDataUtils.java#L63) | `String` | uscc(`String`) | 查询企业信用报告 |
| [queryAssess](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/irs/CreditDataUtils.java#L97) | `Map<String, Object>` | uscc(`String`) | 查询企业信用评价 |
| [querySeriousdishonesty](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/irs/CreditDataUtils.java#L133) | `boolean` | uscc(`String`)<br/>ztmc(`String`) | 严重失信名单查询 |
