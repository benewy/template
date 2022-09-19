package com.beneway.web.config;

import com.beneway.basic.config.AppConfig;
import com.beneway.web.interceptor.ReqLogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import java.util.logging.Logger;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  public static Logger log = Logger.getLogger("com.beneway.web.config.WebMvcConfig");

  @Resource
  private AppConfig appConfig;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ReqLogInterceptor())
      .addPathPatterns("/**");

    String[] excludePaths = appConfig.getExcludePaths();
    if (excludePaths != null) {
      for (String excludePath : excludePaths) {
        log.info("excludepath:" + excludePath);
      }
    }

    registry.addInterceptor(new AuthInterceptor())
      .addPathPatterns("/**")
      .excludePathPatterns(excludePaths == null ? new String[0] : excludePaths);
  }

  // 设置跨域访问
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")
      .allowCredentials(true);
  }

  @Bean
  public FilterRegistrationBean xssFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setDispatcherTypes(DispatcherType.REQUEST);
    registration.setFilter(new XssFilter());
    registration.addUrlPatterns("/*");
    registration.setName("xssFilter");
    registration.setOrder(Integer.MAX_VALUE);
    return registration;
  }
}
