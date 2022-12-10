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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ExecutorBuilder;
import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.log.log_req.entity.logReq.LogReq;
import com.beneway.basic.log.log_req.service.LogReqService;
import com.beneway.basic.utils.AppUtils;
import com.beneway.basic.utils.IPUtil;
import com.beneway.basic.utils.SpringContextHolder;
import com.beneway.web.exception.AutoExceptionInfo;
import com.beneway.web.interceptor.service.LoginUserService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;


public class ReqLogInterceptor implements HandlerInterceptor, DisposableBean {

  public static Logger log = Logger.getLogger("com.beneway.web.interceptor.ReqLogInterceptor");

  private static final Gson gson = new Gson();

  private static final ThreadLocal<Long> threadLocal = new ThreadLocal();

  private LoginUserService loginuserService = SpringContextHolder.getBean(LoginUserService.class);

  private LogReqService logReqService = SpringContextHolder.getBean(LogReqService.class);

  private AppUtils appUtils = SpringContextHolder.getBean(AppUtils.class);

  /**
   * 保存请求日志线程池
   */
  private ExecutorService executor;

  /**
   * 构造函数 初始化保存请求日志线程池
   */
  public ReqLogInterceptor() {
    log.info(getClass().getName() + " 构建初始化线程池");
    executor = ExecutorBuilder.create()
      .setCorePoolSize(3)
      .setMaxPoolSize(10)
      .setWorkQueue(new LinkedBlockingQueue<>(10))
      .build();
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // 保存请求开始时的时间毫秒值
    long timeMillis = System.currentTimeMillis();
    threadLocal.set(timeMillis);
    return true;
  }

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

  /**
   * 对象销毁 关闭线程池
   * @throws Exception
   */
  @Override
  public void destroy() throws Exception {
    executor.shutdown();
  }

}
