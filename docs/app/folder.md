# 目录说明

```shell
.
├── basic # 基础模块相关
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.beneway.basic
│   │   │   │       ├── config # 基础模块配置文件
│   │   │   │       ├── enums # 枚举
│   │   │   │       ├── exception # 自定义异常
│   │   │   │       ├── log # 记录操作日志
│   │   │   │       ├── mybatisplus # MyBatisPlus配置
│   │   │   │       ├── system  # 基础服务
│   │   │   │       │   ├── sys_agency # 部门服务
│   │   │   │       │   ├── sys_agency_user # 部门用户服务
│   │   │   │       │   ├── sys_area # 行政区域服务
│   │   │   │       │   ├── sys_config # 系统配置服务
│   │   │   │       │   ├── sys_files # 文件服务
│   │   │   │       │   ├── sys_filter_unit # 单位筛选服务
│   │   │   │       │   ├── sys_filter_user # 用户筛选服务
│   │   │   │       │   ├── sys_menu # 路由菜单服务
│   │   │   │       │   ├── sys_message # 信息服务
│   │   │   │       │   ├── sys_role # 权限服务
│   │   │   │       │   ├── sys_tag # 标签服务
│   │   │   │       │   ├── sys_unit # 单位服务
│   │   │   │       │   ├── sys_user # 用户服务
│   │   │   │       │   ├── sys_user_unit # 用户单位关联服务
│   │   │   │       │   └── token # 第三方token记录
│   │   │   │       └── utils # 工具类
│   │   │   └── resources # 资源文件
│   │   └── test # 单元测试
│   ├── README.md # 基础模块介绍说明
│   └── pom.xml # 基础模块依赖
├── core # 核心模块
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.beneway.core
│   │   │   │       ├── config # 配置文件
│   │   │   │       ├── controller # 前端控制器
│   │   │   │       ├── entity.sys_user.fo # 用户登录表单实体
│   │   │   │       ├── service.sys_user # 用户登录服务
│   │   │   │       └── Core # 主入口
│   │   │   └── resources # 资源文件
│   │   └── test # 单元测试
│   ├── README.md # 核心模块介绍说明
│   └── pom.xml # 核心模块依赖
├── web # web模块
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── com.beneway.web
│   │   │           ├── annotation # 自定义注解
│   │   │           ├── config # 配置文件
│   │   │           ├── controller # 前端控制器
│   │   │           ├── exception # 自定义异常
│   │   │           ├── interceptor # 系统拦截器
│   │   │           ├── service # 登录服务
│   │   │           ├── utils # 工具类
│   │   │           └── xss # xss防护
│   │   └── test # 单元测试
│   ├── README.md # web模块介绍说明
│   └── pom.xml # web模块依赖
├── README.md # 介绍说明
├── pom.xml # maven依赖管理
```
