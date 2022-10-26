---
outline: deep
---

# SysArea 行政区域

> 操作行政区域有关的接口

以下接口说明来源于项目根目录下 [SysAreaController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysAreaController.java) 文件，可自行查看详情

## 获取区域选择列表

### 请求说明

获取行政区域信息

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_area/getSelectList`


### 请求参数

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 行政区域id编号 |
| pid | 是 | `Integer` | 父级id |
| name | 是 | `String` | 行政区域名称 |
| level | 是 | `SysAreaLevelEnum` | 级别 1：省级 2：市级 3：区县级 |
