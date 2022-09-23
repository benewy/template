/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

package com.beneway.web.config;

import com.beneway.basic.config.AppConfig;
import com.beneway.web.interceptor.AuthInterceptor;
import com.beneway.web.interceptor.ReqLogInterceptor;
import com.beneway.web.xss.XssFilter;
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
