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
