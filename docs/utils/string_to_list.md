---
outline: deep
---

# 类型转换工具

> 用于不同类型之间的转换

## StringToList

> 用于类型转换工作

### Usage

示例：

```java
  public void test() {
      ...
      // 将 value 转成 List<String> 类型的数据
      String value = "王明,张强,李凡";
      List<String> data = StringToList.stringToList(value, split);
      // 结果:data = ['王明','张强','李凡']
      ...
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [stringToList](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/string_to_list/StringToList.java#L31) | `List<String>` | value(`String`)<br/>split(`String`) | 将字符串转换成list |
| [SysAgencyToNameList](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/string_to_list/StringToList.java#L36) | `String` | lists(`List<SysAgency>`) | 将数组lists中所有SysAgency的name属性放入数组，并以String类型输出 |
