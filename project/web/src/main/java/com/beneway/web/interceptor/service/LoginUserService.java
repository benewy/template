package com.beneway.web.interceptor.service;

import com.beneway.basic.common.result.Result;
import com.beneway.basic.common.annotation.ReqApi;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/11 17:39
 */
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
