---
outline: deep
---

# Redis缓存工具

> 管理并操作Redis中的缓存数据

## RedisUtils

> 操作Redis中的数据

### Usage

示例：

```java
  @Resource
  private RedisUtils redisUtils;

  public void test(String loginNumRedisKey, Object loginNum, long loginNumOutTime) {
    ...
    redisUtils.set(loginNumRedisKey, loginNum, loginNumOutTime);
    ...
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [set](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/RedisUtils.java#L43) | - | key(`String`)<br/>value(`Object`)<br/>expire(`long`) | 保存数据到缓存并设置过期时间 |
| [set](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/RedisUtils.java#L50) | - | key(`String`)<br/>value(`Object`) | 保存数据到缓存 |
| [get](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/RedisUtils.java#L54) | `String` | key(`String`)<br/>clazz(`Class<T>`)<br/>expire(`long`) | 获取缓存中指定key的数据,转换成clazz对象并设置过期时间 |
| [get](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/RedisUtils.java#L62) | `String` | key(`String`)<br/>clazz(`Class<T>`) | 获取缓存中指定key的数据并转换成clazz对象 |
| [get](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/RedisUtils.java#L66) | `String` | key(`String`)<br/>expire(`long`) | 获取缓存中指定key的数据并设置过期时间 |
| [get](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/RedisUtils.java#L74) | `String` | key(`String`) | 获取缓存中指定key的数据 |
| [delete](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/RedisUtils.java#L78) | - | key(`String`) | 删除缓存中指定key的数据 |
| [toJson](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/RedisUtils.java#L85) | `String` | object(`Object`) | Object转成JSON数据 |
| [fromJson](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/RedisUtils.java#L96) | `T` | json(`String`)<br/>clazz(`Class<T>`) | JSON数据，转成Object |
