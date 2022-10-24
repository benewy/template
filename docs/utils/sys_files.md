---
outline: deep
---

# 系统文件工具

> 用于对系统中的文件进行操作

## FilePathEnum

> 用于存放文件的操作路径

该文件位于根项目下 [FilePathEnum](https://github.com/elonehoo/benewy-template/blob/docs/project/basic/src/main/java/com/beneway/basic/utils/sys_files/FilePathEnum.java) 文件，具体细节可查看此文件

## FileTypeEnum

> 用于存放文件的类型

该文件位于根项目下 [FileTypeEnum](https://github.com/elonehoo/benewy-template/blob/docs/project/basic/src/main/java/com/beneway/basic/utils/sys_files/FileTypeEnum.java) 文件，具体细节可查看此文件

## SysFilesUtils

> 用于操作系统中的文件

### Usage

示例：

```java
  @Resource
  private SysFilesUtils sysFilesUtils;

  @Override
  public File getFile(SysFiles sysFiles) {
    ...
    String path = sysFiles.getPath();
    String id = sysFiles.getId();
    String realPath = sysFilesUtils.packStorPath(path, id);
    File file = new File(realPath);
    return file;
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [setFilePath](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/sys_files/SysFilesUtils.java#L64) | - | filePath(`String`) | 设置根文件存储路径 |
| [createPath](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/sys_files/SysFilesUtils.java#L94) | `File` | path(`String`) | 创建文件目录 |
| [packStorPath](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/sys_files/SysFilesUtils.java#L103) | `String` | paths(`String[]`) | 获取路径 |
| [packStorPath](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/sys_files/SysFilesUtils.java#L118) | `String` | filePathEnum(`FilePathEnum`)<br/>paths(`String[]`) | 获取路径 |
| [getCurrentFilesPath](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/sys_files/SysFilesUtils.java#L131) | `String` | - | 获取当前files下的目录 |
