---
outline: deep
---

# 应用上下文工具

> 获取系统应用中的上下文，并操作容器中的对象

## SpringContextHolder

> 获取应用系统上下文、Spring容器中的bean等

### Usage

示例：

```java
  public static void main(String[] args) {
    ...
    // 获取Spring容器中的A对象
    A a = (A) SpringContextHolder.getBean(A.class);
    ...
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [getApplicationContext](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/SpringContextHolder.java#L37) | `ApplicationContext` | - | 获取应用系统上下文 |
| [getBean](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/SpringContextHolder.java#L46) | `T` | name(`String`) | 获取Spring容器中的Bean |
| [getBean](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/SpringContextHolder.java#L54) | `T` | requiredType(`Class<T>`) | 获取Spring容器中的Bean |
| [clearHolder](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/SpringContextHolder.java#L62) | - | - | 清除SpringContextHolder中的ApplicationContext属性 |
