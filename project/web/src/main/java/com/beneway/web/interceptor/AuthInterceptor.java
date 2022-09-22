package com.beneway.web.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.beneway.basic.utils.SpringContextHolder;
import com.beneway.web.annotation.ReqApi;
import com.beneway.web.exception.AutoExceptionInfo;
import com.beneway.web.interceptor.service.LoginUserService;
import com.google.gson.Gson;
import com.restful.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    HttpStatus code = result.getStatusCode();
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
