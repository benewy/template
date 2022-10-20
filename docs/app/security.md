---
outline: deep
---

# 安全防护

## Xss 安全防护配置

Xss 配置文件存在于项目根目录下 [xss](https://github.com/elonehoo/benewy-template/tree/main/project/web/src/main/java/com/beneway/web/xss) 包内及文件 [WebMvcConfig](https://github.com/elonehoo/benewy-template/blob/main/project/web/src/main/java/com/beneway/web/config/WebMvcConfig.java)

### Xss 包 [/XssAndSqlHttpServletRequestWrapper](https://github.com/elonehoo/benewy-template/tree/main/project/web/src/main/java/com/beneway/web/xss/XssAndSqlHttpServletRequestWrapper.java)

> 自定义包装类，作用为对所请求的参数进行 Xss 过滤

```java
package com.beneway.web.xss;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @ClassName:  XssAndSqlHttpServletRequestWrapper
 * @Description:(xxsfileter 包装类)
 *
 */
public class XssAndSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    /**
     * 假如有有html 代码是自己传来的  需要设定对应的name 不走 HtmlUtil.escape(value) 过滤
     */
    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (StrUtil.isNotEmpty(value)) {
            // 转义文本中的HTML字符为安全的字符
            value = HtmlUtil.escape(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (parameterValues == null) {
            return null;
        }
        for (int i = 0; i < parameterValues.length; i++) {
            String value = parameterValues[i];
        }
        return parameterValues;
    }
}
```

### Xss 包 [/XssFilter](https://github.com/elonehoo/benewy-template/tree/main/project/web/src/main/java/com/beneway/web/xss/XssFilter.java)

> 对请求体中的数据进行过滤，并注入对 Xss 的解析器

```java
package com.beneway.web.xss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter implements Filter {

  @Override
  public void destroy() {
    //  Auto-generated method stub

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    //  Auto-generated method stub
    HttpServletRequest req = (HttpServletRequest) request;
    XssAndSqlHttpServletRequestWrapper xssRequestWrapper = new XssAndSqlHttpServletRequestWrapper(req);
//        BodyReaderHttpServletRequestWrapper bodyReaderHttpServletRequestWrapper = new BodyReaderHttpServletRequestWrapper(xssRequestWrapper);
    chain.doFilter(xssRequestWrapper, response);
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    //  Auto-generated method stub
  }

  @Bean
  @Primary
  public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
    // 解析器
    ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    // 注册xss解析器
    SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
    xssModule.addSerializer(new XssStringJsonSerializer());
    objectMapper.registerModule(xssModule);
    // 返回
    return objectMapper;
  }
}
```

### Xss 包 [/XssStringJsonSerializer](https://github.com/elonehoo/benewy-template/tree/main/project/web/src/main/java/com/beneway/web/xss/XssStringJsonSerializer.java)

> 作用为对 json 数据进行过滤

```java
package com.beneway.web.xss;

import cn.hutool.http.HtmlUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 *
 * @ClassName:  XssStringJsonSerializer
 * @Description:(实现过滤json类型)
 *
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {

    @Override
    public Class<String> handledType() {
        return String.class;
    }

    /**
     * 假如有有html 代码是自己传来的  需要设定对应的name 不走StringEscapeUtils.escapeHtml4(value) 过滤
     */
    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (value != null) {
            //  转义 value 中的HTML字符为安全的字符
            String encodedValue = HtmlUtil.escape(value);
            jsonGenerator.writeString(encodedValue);
        }
    }

}
```

### WebMvcConfig

> 此处代码示例仅将 XssFilter 过滤器注册并交给IOC容器管理

```java
package com.beneway.web.config;

import com.beneway.web.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  // 将自定义的XssFilter注册并交给IOC容器管理
  @Bean
  public FilterRegistrationBean xssFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    // filter 过滤器的触发方式为REQUEST
    registration.setDispatcherTypes(DispatcherType.REQUEST);
    registration.setFilter(new XssFilter());
    registration.addUrlPatterns("/*");
    registration.setName("xssFilter");
    // 将此过滤器放置在过滤链的最后一个
    registration.setOrder(Integer.MAX_VALUE);
    return registration;
  }
}
```
