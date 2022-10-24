---
outline: deep
---

# 文件操作

> 对文件进行操作的工具

## FileRuntimeException

该异常为对文件进行操作时，发生内部异常捕获之后对其进行异常的二次包装

详细内容可查看[此处](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/file/FileRuntimeException.java)

## FileUtil

> 对文件进行操作的工具类

### Usage

示例：

```java
public void test() {
  ...
  try {
    FileUtil.inputFile(file.getInputStream(), filePath);
  } catch (IOException e) {
    throw new FileRuntimeException(e);
  }
  ...
}
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [inputFile](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/file/FileUtil.java#L26) | - | inputStream(`InputStream`)<br/>filePath(`String`) | 输入文件到inputStream流中 |
| [outputFile](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/file/FileUtil.java#L40) | - | outputStream(`OutputStream`)<br/>filePath(`String`) | 输出文件到outputStream流中 |
| [copyFile](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/file/FileUtil.java#L54) | - | sourceFilePath(`String`)<br/>targetFilePath(`String`) | copy文件 |
| [flow](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/file/FileUtil.java#L64) | - | inputStream(`InputStream`)<br/>outputStream(`OutputStream`) | 将输入流写出 |

