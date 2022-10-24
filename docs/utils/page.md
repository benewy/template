---
outline: deep
---

# page 分页工具

> 用于对分页对象的操作

## PageQuery

PageQuery是分页对象的查询实体

在使用过程中如若要使用分页功能，则只需让Fo对象继承该实体，并使用PageUtils进行转换即可

## PageUtils

> 将`PageQuery`对象转换成`com.baomidou.mybatisplus.extension.plugins.pagination.Page`，以便使用分页功能

### Usage

示例：

```java
  @Override
  public Page<SysRoleVo> queryPage(SysRolePageQueryFo sysRolePageQueryFo) {
    ...
    // 将 SysRolePageQueryFo 转换成 Page 对象
    Page page = PageUtils.getPage(sysRolePageQueryFo);
    ...
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [getPage](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/page/PageUtils.java#L28) | `Page` | pageQuery(`PageQuery`) | 将 PageQuery 转换成 Page 对象 |
| [getPage](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/page/PageUtils.java#L33) | `Page` | param(`Map<String, Object>`) | 从param中获取page、size属性，并返回Page对象 |
