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

package com.beneway.web.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.beneway.basic.utils.SpringContextHolder;
import com.beneway.web.annotation.ReqApi;
import com.beneway.web.exception.AutoExceptionInfo;
import com.beneway.web.interceptor.service.LoginUserService;
import com.google.gson.Gson;
import com.restful.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {

  private static final Gson gson = new Gson();

  private LoginUserService loginuserService = SpringContextHolder.getBean(LoginUserService.class);

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
    HttpStatus code = HttpStatus.resolve(result.getStatusCode().value());
    if (HttpStatus.OK.equals(code)) {
      return true;
    } else {
      error(response, result, null);
      return false;
    }

  }

  private void error( HttpServletResponse httpServletResponse, Result result, Exception exception){

    AutoExceptionInfo.setExceptionInfo(result.getStatusCode().value(), result.getBody().toString(), exception);
    try {
      httpServletResponse.setCharacterEncoding("utf-8");
      httpServletResponse.setContentType("application/json; charset=utf-8");
      httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
      httpServletResponse.setHeader("Cache-Control", "no-cache");
      PrintWriter out = httpServletResponse.getWriter();
      String res = gson.toJson(result);
      out.write(res);
      out.flush();
      out.close();
    }catch (Exception e){
      e.printStackTrace();
    }
  }

}
