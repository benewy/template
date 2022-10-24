---
outline: deep
---

# 二维码工具

> 主要用于对二维码的创建和识别功能

## Code2DUtils

> 二维码的创建和识别功能

### Usage

示例：

```java
  public static void main(String[] args) {
    ...
    // 用于识别二维码中的内容
    String decode = Code2DUtils.decode("filepath");
    ...
  }
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [imageGeneration](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/Code2DUtils.java#L48) | `File` | bufferedImage(`BufferedImage`)<br/>title(`String`)<br/>height(`int`) | 生成图像 |
| [createCode](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/Code2DUtils.java#L136) | `BufferedImage` | url(`String`)<br/>content(`String`)<br/>width(`int`)<br/>height(`int`) | 生成二维码 |
| [decode](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/Code2DUtils.java#L169) | `String` | address(`String`)<br/>ztmc(`String`) | 识别二维码 |
