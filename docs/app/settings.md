---
outline: deep
---

# 环境配置

> 用于修改项目的全局配置、MyBatis-Plus配置、Redis配置、Sa-Token配置、钉钉配置等等

## 全局配置

项目的环境变量配置位于项目根目录下的 [application.yml](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/resources/application.yml)、[application-dev.yml](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/resources/application-dev.yml)、[application-basic.yml](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/resources/application-basic.yml)、[application-basic-dev.yml](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/resources/application-basic-dev.yml)

## 配置项说明

### application.yml

> 主配置文件

```yaml
# 服务配置
server:
  servlet:
    context-path: /lists # 项目路径
# spring 多环境配置
spring:
  profiles:
    include: basic,dev,basic-dev # 引入环境配置文件
# 应用配置
appConfig:
  moduleType: admin # 模块类型
  # 排除访问路径
  excludePaths: /admin_sys_user/login,/admin_sys_user/qrLogin,/login/getLoginVerifyImg
```

### application-dev.yml

> 开发环境适用

```yaml
server:
  port: 8080 # 端口号
spring:
  servlet:
    multipart:
      enabled: true # 支持文件上传
      max-file-size: 50MB # 上传文件大小
      max-request-size: 100MB # 数据总大小
  redis:
    database: 0 # 数据库编号
    host: 127.0.0.1 # ip地址
    port: 6379 # 访问端口
    password:      # 密码（默认为空）
    timeout: 6000ms  # 连接超时时长（毫秒）
    lettuce:
      pool:
        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）
        max-wait: 10000ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）
        max-idle: 10      # 连接池中的最大空闲连接
        min-idle: 5       # 连接池中的最小空闲连接
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl # 打印SQL语句
# Sa-Token配置
sa-token:
  # token有效期，单位s 默认30天, -1代表永不过期
  timeout: 2592000
  # token临时有效期 (指定时间内无操作就视为token过期) 单位: 秒
  activity-timeout: -1
  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)
  is-concurrent: true
  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)
  is-share: false
  # 是否输出操作日志
  is-log: true
```

### application-basic.yml

> 所有的环境使用都适用

```yaml
spring:
  config:
    activate:
      on-profile: "basic"
mybatis-plus:
  mapper-locations: classpath:com/beneway/basic/**/mapper/*.xml # 扫描Mapper所对应的XML文件位置
  type-aliases-package: com.beneway.**.entity # 包扫描路径
  global-config:  # MyBatis-Plus全局配置
    banner: false # 是否控制台 print mybatis-plus 的 LOGO
    db-config: # 数据库配置
      id-type: ASSIGN_UUID # 全局默认主键类型
      logic-delete-field: deleted # 逻辑删除字段属性名
      logic-delete-value: 1 # 逻辑已删除值
      logic-not-delete-value: 0 # 逻辑未删除值
  configuration:
    map-underscore-to-camel-case: true # 是否开启自动驼峰命名规则映射
sa-token:
  is-print: false # 是否在初始化配置时打印版本字符画
  token-name: access_token # Token 名称
  token-style: tik # token风格

```

### application-basic-dev.yml

> 开发环境适用

```yaml
spring:
  config:
    activate:
      on-profile: "basic-dev"
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      name: tl-seven-lists
      url: jdbc:mysql://192.168.0.128:3307/tl-seven-lists?characterEncoding=utf-8&serverTimezone=Asia/Shanghai
      username: root
      password: Beneway2021
      driver-class-name: com.mysql.cj.jdbc.Driver
      initial-size: 10
      max-active: 100
      min-idle: 10
      max-wait: 60000
      pool-prepared-statements: true
      max-pool-prepared-statement-per-connection-size: 20
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 300000
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      stat-view-servlet:
        enabled: true
        url-pattern: /druid/*
        login-username: admin
        login-password: 123456
      filter:
        config:
          enabled: true
        stat:
          log-slow-sql: true
          slow-sql-millis: 1000
          merge-sql: false
        wall:
          config:
            multi-statement-allow: true
# 钉钉配置信息
dd-config:
  domainname: openplatform-pro.ding.zj.gov.cn
  tenantId: 196729
  pc:
    key:
    secret:
    appId:
  qrcode:
    key: zsht-ydjr_dingoa-cab47knRJraym
    secret: ht6X4YAUMHIwwDX0G0E23A4rwTNtKA71HL5vJ25k
  phone:
    key: zsht-m15L07V1tCqlak9kfgS3QNfIj
    secret: ish1fmuCeotCs6V99H2j4AUIeDI0COD5Ov43n5R2
    appId:
```
