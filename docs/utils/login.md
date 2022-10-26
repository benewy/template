---
outline: deep
---

# Login 登录资源

> 登录时需要操作的前置资源

以下接口说明来源于项目根目录下 [LoginController](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/controller/LoginController.java) 文件，可自行查看详情


## 获取登录验证图片

### 请求说明

该接口将会在请求后返回一张图片，为图形验证码

**该接口无请求参数**

### 请求路径

HTTP方法： `GET`

请求URL：`http://localhost:8080/lists/login/getLoginVerifyImg`
