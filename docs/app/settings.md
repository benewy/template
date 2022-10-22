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
    type: com.alibaba.druid.pool.DruidDataSource # 数据源类型
    druid:
      name: tl-seven-lists # 数据源名称
      # 连接数据库的url
      url: jdbc:mysql://192.168.0.128:3307/tl-seven-lists?characterEncoding=utf-8&serverTimezone=Asia/Shanghai
      username: root # 连接数据库的用户名
      password: Beneway2021 # 连接数据库的密码
      driver-class-name: com.mysql.cj.jdbc.Driver # 驱动
      initial-size: 10 # 初始化时建立物理连接的个数
      max-active: 100 # 最大连接池数量
      min-idle: 10 # 最小连接池数量
      max-wait: 60000 # 获取连接时最大等待时间
      pool-prepared-statements: true # 是否缓存preparedStatement
      max-pool-prepared-statement-per-connection-size: 20 # 要启用PSCache
      time-between-eviction-runs-millis: 60000 # 检测连接的间隔时间
      min-evictable-idle-time-millis: 300000 # 连接保持空闲而不被驱逐的最小时间
      test-while-idle: true # 执行validationQuery检测连接是否有效
      test-on-borrow: false # 申请连接时执行validationQuery检测连接是否有效
      test-on-return: false # 归还连接时执行validationQuery检测连接是否有效
      stat-view-servlet:
        enabled: true # 启用DruidStatViewServlet
        url-pattern: /druid/*
        login-username: admin # 登录名
        login-password: 123456 # 登录密码
      filter:
        config:
          enabled: true # 启用监控统计拦截
        stat:
          log-slow-sql: true # 开启慢查询记录
          slow-sql-millis: 1000 # 慢SQL记录标准，超过1000ms，就是慢查询
          merge-sql: false # 是否使用sql合并统计
        wall:
          config:
            multi-statement-allow: true # 是否允许一次执行多条语句
# 钉钉配置信息
dd-config:
  domainname: openplatform-pro.ding.zj.gov.cn # 政务钉钉的域名
  tenantId: 196729 # 租户 id
  pc:
    key:
    secret:
    appId:
  qrcode:
    key: zsht-ydjr_dingoa-cab47knRJraym # AccessKey秘钥
    secret: ht6X4YAUMHIwwDX0G0E23A4rwTNtKA71HL5vJ25k # SecretKey秘钥
  phone:
    key: zsht-m15L07V1tCqlak9kfgS3QNfIj # AccessKey秘钥
    secret: ish1fmuCeotCs6V99H2j4AUIeDI0COD5Ov43n5R2 # SecretKey秘钥
    appId:
```

## 自定义应用配置

> 加载自定义应用配置，扩展应用配置需求

::: tip 提示

如若还需要添加其他的应用配置，可自行在 [application.yml](./settings#application-yml) 文件的`appConfig`下添加所需属性，并在 [AppConfig](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/config/AppConfig.java) 中添加其私有变量和对应方法，可参考如下示例代码

:::

````java
package com.beneway.basic.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {

    @Value("${spring.profiles.include}")
    private String[] actives;

    @Value("${appConfig.excludePaths}")
    private String[] excludePaths;

    @Value("${appConfig.moduleType}")
    private String moduleType;

    public void setActive(String[] actives) {
      if (this.actives == null){
        this.actives = actives;
      }
    }

    public void setExcludePaths(String[] excludePaths) {
        if (this.excludePaths == null) {
            this.excludePaths = excludePaths;
        }
    }

    public void setModuleType(String moduleType) {
        if (this.moduleType == null) {
            this.moduleType = moduleType;
        }
    }
}
````

## WebMVC 配置

项目的WebMVC文件位于项目根项目下 [WebMvcConfig](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/config/WebMvcConfig.java)

> 用于配置WebMVC的拦截器、跨域请求、静态资源等

### 拦截器配置

> 用于日志记录、登录判断、权限检查等作用

```java
package com.beneway.web.config;

import com.beneway.basic.config.AppConfig;
import com.beneway.web.interceptor.AuthInterceptor;
import com.beneway.web.interceptor.ReqLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static Logger log = Logger.getLogger("com.beneway.web.config.WebMvcConfig");

    @Resource
    private AppConfig appConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加访问日志记录拦截器，匹配所有路径
        registry.addInterceptor(new ReqLogInterceptor())
                .addPathPatterns("/**");

        // 全局排除的访问路径
        String[] excludePaths = appConfig.getExcludePaths();
        if (excludePaths != null) {
            for (String excludePath : excludePaths) {
                log.info("excludepath:" + excludePath);
            }
        }

        // 添加权限校验拦截器，匹配所有路径，指定排除的访问路径，如若未指定则放入一个长度为0的空数组
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePaths == null ? new String[0] : excludePaths);
    }
}
```

#### 日志记录拦截器

> 其作用为拦截所有请求，并对所有请求进行记录

以下为核心代码，可查看 [ReqLogInterceptor](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/interceptor/ReqLogInterceptor.java) 文件的具体配置

```java
@Override
public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  if ("OPTIONS".equals(request.getMethod())){
    return;
  }

  try {
    long startTime = threadLocal.get();
    threadLocal.remove();

    long endTime = System.currentTimeMillis();
    // 获取请求url
    String requestURI = request.getRequestURI();
    // 获取请求方法
    String method = request.getMethod();
    // 获取请求ip
    String ip = IPUtil.getIpAddr(request);
    // 获取请求用户名
    String username = loginuserService.getUsername();

    // 获取请求params
    String params = null;
    Map<String, String[]> parameterMap = request.getParameterMap();
    if (CollUtil.isNotEmpty(parameterMap)) {
      params = gson.toJson(parameterMap);
    }

    // 获取请求body
    String body = null;

    // 获取登录用户id
    String loginUserId = null;
    try {
      loginUserId = appUtils.getLoginUserId();
    } catch (NullPointerException e) {

    } catch (Exception e) {
      log.warning("获取用户id失败");
    }

    // 计算请求执行耗时
    long duration = endTime - startTime;

    String finalParams = params;
    String finalBody = body;
    String finalLoginUserId = loginUserId;
    AutoExceptionInfo autoExceptionInfo = AutoExceptionInfo.getExceptionInfo();
    Future<?> submit = executor.submit(() -> {
      // 日志封装
      String logs = String.format("用户：%s，uri：%s，method：%s，ip：%s，执行耗时：%dms，params：%s, body：%s", username, requestURI, method, ip, duration, finalParams, finalBody);
      // 查看是否有异常信息
      boolean isError = false;
      if (autoExceptionInfo != null) {
        logs = "errCode：" + autoExceptionInfo.getCode() + "，errMsg：" + autoExceptionInfo.getMsg() + "，" + logs;
        log.warning(logs + autoExceptionInfo.getE());
        isError = true;
      } else {
        log.info(logs);
      }

      // 保存日志信息
      LogReq logReq = new LogReq();
      logReq.setUserId(finalLoginUserId);
      UserTypeEnum loginUserType = appUtils.getLoginUserType();
      logReq.setUserType(loginUserType);
      logReq.setReqUrl(requestURI);
      logReq.setReqMethod(method);
      logReq.setReqIp(ip);
      logReq.setReqParams(finalParams);
      logReq.setReqBody(finalBody);
      logReq.setDuration((int)duration);
      logReq.setIsError(isError);
      if (isError) {
        Throwable throwable = autoExceptionInfo.getE();
        if (throwable != null) {
          PrintWriter pw = null;
          try {
            StringWriter sw = new StringWriter();
            pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            logReq.setErrorInfo(sw.toString());
          } catch (Exception e) {
            log.warning("请求日志异常信息获取失败 " + e);
          } finally {
            if (pw != null) {
            pw.close();
            }
          }
        }
        int code = autoExceptionInfo.getCode();
        String errorCode = String.valueOf(code);
        logReq.setErrorCode(errorCode);
        logReq.setErrorMsg(autoExceptionInfo.getMsg());
      }
      logReq.setModuleType(appUtils.getModuleType());
      logReq.setCreateTime(new Date());

      logReqService.save(logReq);
    });

    submit.get();

  } catch (Exception e) {
    e.printStackTrace();
    log.warning("请求日志处理错误!" + e);
  }
}
```

#### 权限校验拦截器

> 校验用户的访问的权限

以下为核心代码，可查看 [AuthInterceptor](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/interceptor/AuthInterceptor.java) 文件的具体配置

```java
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
    if ("OPTIONS".equals(request.getMethod())) {
      return true;
    }

    if (!(object instanceof HandlerMethod)) {
      return true;
    }

    HandlerMethod handlerMethod = (HandlerMethod) object;
    Method method = handlerMethod.getMethod();

    // 获取请求用户id
    String userId = null;
    try {
      userId = StpUtil.getLoginIdAsString();
    } catch (Exception e) {
      // 获取用户id失败，封装错误信息
      String message = e.getMessage();
      Result result = Result.internalServerError(message);
      error(response, result, e);
      return false;
    }

    ReqApi reqApi = method.getAnnotation(ReqApi.class);

    // 校验
    Result result = loginuserService.checkUser(userId, reqApi);
    HttpStatus code = result.getStatusCode();
    if (HttpStatus.OK.equals(code)) {
      return true;
    } else {
      error(response, result, null);
      return false;
    }

  }
```

### 跨域请求配置

> 用于设置跨域资源共享

```java
package com.beneway.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 设置跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 允许所有域名访问
                .allowedOrigins("*")
                // 允许的请求方式
                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")
                // 允许发送Cookie
                .allowCredentials(true);
    }
}
```

## MyBatis-Plus 配置

> 用于配置MyBatis-Plus插件、扩展方法等

### 全局配置

MyBatis-Plus默认全局配置在 [basic/config/MyBatisPlusConfig](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/config/MyBatisPlusConfig.java) 内配置

```java
package com.beneway.basic.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {

  /**
   * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    return interceptor;
  }

}
```

### 扩展方法

> 用于扩展 MyBatis-Plus 默认提供的方法，丰富 IService 接口。
>
> 扩展文件位于项目根项目下 [basic/mybatisplus/MyIService](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/mybatisplus/MyIService.java)

::: warning 注意
所有新创建的 Service 接口需继承 MyIService 接口即可使用扩展的方法
:::

```java
// 继承于MyBatis-Plus的IService接口
package com.beneway.basic.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MyIService<T> extends IService<T> {

  default boolean isExist(Wrapper<T> queryWrapper){
    return count(queryWrapper) > 0;
  }

}
```

## Redis配置

> Redis 是一个高性能的key-value数据库，用于缓存项目中高频请求数据，减小后台数据库压力。同时也能为其他已接入的框架提供缓存功能

Redis 相关配置属性移步 [application-dev.yml](./settings#application-dev-yml) 查看详情

::: warning 注意
若要使用 Redis 的缓存功能，需要在主入口（[Core](https://github.com/elonehoo/benewy-template/blob/main/project/core/src/main/java/com/beneway/core/Core.java)）上加上`@EnableCaching` 注解，即可开启缓存功能
:::

### 初始化 Redis 配置

```java
package com.beneway.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * Redis配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class RedisConfig {
    @Resource
    private RedisConnectionFactory factory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 使用StringRedisSerializer进行序列化，只能存储类型为String的Key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 只能存储类型为String的HashKey
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 只能存储类型为String的HashValue
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        // 使用JdkSerializationRedisSerializer进行序列化，只能存储Jdk序列化的value
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
```

## Sa-Token配置

Sa-Token 的全局配置项请移步 [application-dev.yml](./settings#application-dev-yml)、[application-basic.yml](./settings#application-basic-yml) 查看详情

> 关于 Sa-Token 的更多配置，可查看[此处](https://sa-token.dev33.cn/doc.html#/use/config?id=%e6%89%80%e6%9c%89%e5%8f%af%e9%85%8d%e7%bd%ae%e9%a1%b9)

## 钉钉配置

政务钉钉的全局配置项请移步 [application-basic-dev.yml](./settings#application-basic-dev-yml) 查看详情

### 初始化数据

初始化配置文件存在于项目根目录下 [StartService](https://github.com/elonehoo/benewy-template/blob/main/project/basic/src/main/java/com/beneway/basic/utils/dd/StartService.java)

```java
package com.beneway.basic.utils.dd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 服务启动时执行的方法
 */
@Slf4j
@Component
public class StartService implements ApplicationRunner {

    @Resource
    private ZWDDQrcodeUtils zwddQrcodeUtils;

    @Resource
    private ZWDDPhoneUtils zwddPhoneUtils;

    /**
     * 进行小程序token的初次获取
     */
    @Override
    public void run(ApplicationArguments args) {
        // 刷新政务钉钉二维码的Token属性值
        zwddQrcodeUtils.flushToken();
        // 刷新政务钉钉电话的Token属性值
        zwddPhoneUtils.flushToken();
    }
}
```

## Druid 监控配置

Druid 相关监控配置属性移步 [application-dev.yml](./settings.html#application-basic-dev-yml) 查看 `spring.datasource.druid.stat-view-servlet`

> 用于监控数据库访问性能，详细统计SQL的执行性能，线上分析数据库访问性能

### 初始化 Web 监控 Filter

监控配置文件存在于项目根目录下 [DruidConfig](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/config/DruidConfig.java)


```java
package com.beneway.web.config;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhy
 * @create_date 2019-01-26 20:18
 */

@Configuration
public class DruidConfig {

    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        // 排除静态资源请求监控
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Arrays.asList("/"));
        return bean;
    }

}
```
