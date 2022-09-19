package com.beneway.web.interceptor.service.implement;

import com.beneway.web.annotation.ReqApi;
import com.beneway.web.interceptor.service.LoginUserService;
import com.restful.Result;

public class LoginUserServiceImplements implements LoginUserService {

  @Override
  public Result checkUser(String userId, ReqApi reqApi) {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }
}
