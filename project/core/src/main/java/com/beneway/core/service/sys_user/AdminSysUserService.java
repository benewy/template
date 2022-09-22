package com.beneway.core.service.sys_user;

import com.beneway.core.entity.sys_user.fo.LoginFo;
import com.restful.Result;

public interface AdminSysUserService {

    /**
     * 登录
     *
     * @param loginFo
     * @return
     */
    Result login(LoginFo loginFo);

    /**
     * pc扫码登陆
     *
     * @param code
     * @return
     */
    Result qrLogin(String code);
}
