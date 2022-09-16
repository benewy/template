package com.beneway.web.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.beneway.basic.common.result.Result;
import com.beneway.basic.common.result.ResultCodeEnum;
import com.beneway.basic.common.utils.SpringContextHolder;
import com.beneway.basic.common.annotation.ReqApi;
import com.beneway.basic.common.exception.MyExceptionInfo;
import com.beneway.web.interceptor.service.LoginUserService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2021/7/21
 * @time: 22:06
 *
 * 登陆鉴权拦截器
 */
@Log4j2
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
            Result result = Result.error(ResultCodeEnum.ERROR_TOKEN, message);
            error(response, result, e);
            return false;
        }

        ReqApi reqApi = method.getAnnotation(ReqApi.class);

        // 校验
        Result result = loginuserService.checkUser(userId, reqApi);
        ResultCodeEnum code = result.getCode();
        if (ResultCodeEnum.SUCCESS.equals(code)) {
            return true;
        } else {
            error(response, result, null);
            return false;
        }

    }

    private void error( HttpServletResponse httpServletResponse, Result result, Exception exception){
        MyExceptionInfo.setExceptionInfo(result.getCode().getCode(), result.getMsg(), exception);
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
