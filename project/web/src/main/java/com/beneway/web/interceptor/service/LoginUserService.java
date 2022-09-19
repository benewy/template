package com.beneway.web.interceptor.service;

import com.beneway.web.annotation.ReqApi;
import com.restful.Result;

public interface LoginUserService {

  /**
   * 校验用户
   * @param userId
   * @return
   */
  Result checkUser(String userId, ReqApi reqApi);

  /**
   * 获取用户名
   * @return
   */
  String getUsername();
}
