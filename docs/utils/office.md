---
outline: deep
---

# office文档工具

> 用于操作pdf和word文件

## OfficeToPdf

> 用于将office文档转换成pdf的工具类

### 使用

示例：

```java
public void test(String docFilePath, String pdfFilePath) {
    ...
     OfficeToPdf.word2Pdf(docFilePath, pdfFilePath);
    ...
}
```

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [convert2PDF](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/office/OfficeToPdf.java#L44) | `boolean` | inputFile(`String`)<br/>inputFileSuffix(`String`)<br/>pdfFile(`String`) | 将文件转成PDF文件 |
| [getLicense](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/office/OfficeToPdf.java#L75) | `boolean` | - | 获取凭证license |
| [word2Pdf](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/office/OfficeToPdf.java#L107) | `boolean` | inputFile(`String`)<br/>pdfFile(`String`) | 将word文件转换成pdf文件 |
| [excel2PDF](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/office/OfficeToPdf.java#L139) | `boolean` | inputFile(`String`)<br/>pdfFile(`String`) | 将excel文件转换成pdf文件 |
| [ppt2PDF](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/office/OfficeToPdf.java#L161) | `boolean` | inputFile(`String`)<br/>pdfFile(`String`) | 将ppt文件转换成pdf文件 |

## PdfUtils

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [addWaterMaker](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/office/PdfUtils.java#L52) | - | pdfPath(`String`)<br/>imagePath(`String`)<br/>outPdfPath(`String`) | 给pdf文件添加水印 |

## WordUtils

### Methods

| 方法 | 返回类型 | 入参 | 说明 |
|---|---|---|---|
| [creatWordByModel](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/office/WordUtils.java#L32) | - | tmpFile(`String`)<br/>contentMap(`Map<String, String>`)<br/>exportFile(`String`) | 由模板文件根据contentMap的具体内容生成一个新的word文件 |
| [docToPdf](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/office/WordUtils.java#L64) | - | docFilePath(`String`)<br/>pdfFilePath(`String`)<br/>suffix(`String`) | 将word文件转换成pdf文件 |
