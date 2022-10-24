---
outline: deep
---

# SysFiles 文件操作

> 对文件进行上传下载浏览操作等

以下接口说明来源于项目根目录下 [SysFilesController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysFilesController.java) 文件，可自行查看详情

## 上传文件

### 请求说明

对文件进行上传操作

### 请求路径

HTTP方法： `POST`

请求URL：`http://localhost:8080/lists/sys_files/uploadFile`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| file | 是 | `MultipartFile` | 上传的文件 |
| path | 是 | `String` | 上传路径 |


### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `String` | 返回文件名称(id) |

## 下载文件

### 请求说明

对文件进行下载操作

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_files/downloadFile/{fileId}`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| fileId | 是 | `String` | 文件名称(id)，路径传参 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 预览文件

### 请求说明

对所选文件进行浏览操作

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_files/previewFile/{fileId}`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| fileId | 是 | `String` | 文件名称(id)，路径传参 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 文件删除

### 请求说明

对指定文件进行删除操作

### 请求路径

HTTP方法： `PUT`

请求URL：`http://localhost:8080/lists/sys_files/delFile`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| fileId | 是 | `String` | 文件名称(id) |
| isDel | 否 | `boolean` | 是否物理删除文件，默认为true |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| body | 是 | `T` | 无返回数据 |

## 根据文件id串获取文件信息列表

### 请求说明

通过多个id编号获取多个文件的信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_files/getListByIds`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| ids | 是 | `String` | 多个文件的id串，以`,`进行分隔 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `String` | 文件的id编号 |
| filename | 是 | `String` | 文件的名称 |
| suffix | 是 | `String` | 文件后缀 |
| path | 是 | `String` | 文件路径 |
| createTime | 是 | `Date` | 创建时间 |

