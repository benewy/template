---
outline: deep
---

# 类转换工具

> 用于操作不同类之间的转换工作

## ClassUtil

> 不同类之间的转换

### Usage

示例：

```java
  public static void main(String[] args) {
    ...
    // 将A类转换成B类
    B b = ClassUtil.toClass(A, B.class);
    ...
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [toClass](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/ClassUtil.java#L42) | `T` | object(`Object`)<br/>tClass(`Class<T>`) | 将object对象转换成tClass对象 |
| [toClassList](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/ClassUtil.java#L53) | `List<T>` | objects(`List<? super T>`)<br/>tClass(`Class<T>`) | 将数组中的object对象转换成tClass对象 |
| [toJson](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/ClassUtil.java#L133) | `String` | obj(`Object`) | 将obj对象序列化成json对象 |
| [toStr](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/ClassUtil.java#L133) | `String` | param(`Object`) | 将param转换成String类型 |
