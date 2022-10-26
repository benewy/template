---
outline: deep
---

# SysTag 标签

> 操作与标签相关的接口

以下接口说明来源于项目根目录下 [SysTagController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/system/SysTagController.java) 文件，可自行查看详情


## 获取前端公共组件回显信息

### 请求说明

根据角色id编号获取前端公共回显组件信息

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/sys_tag/getComTagInfo`

### 请求参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 角色id编号 |

### 返回参数

| 参数 | 必选 | 类型 | 说明 |
|---|---|---|---|
| id | 是 | `Integer` | 标签id编号 |
| tagType | 是 | `SysTagTypeEnum` | 标签分类，点击 [详情](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/system/sys_tag/enums/SysTagTypeEnum.java#L6) 查看详细内容 |
| tagName | 是 | `String` | 标签名称 |
