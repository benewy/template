package com.beneway.web.utils;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

  /**
   * 判断请求内容是否为 application/json
   * @param request
   * @return
   */
  public static boolean isRequestContentTypeJson(HttpServletRequest request) {
    String contentType = request.getHeader("Content-Type");
    return StrUtil.isNotEmpty(contentType) && contentType.contains("application/json");
  }

}
