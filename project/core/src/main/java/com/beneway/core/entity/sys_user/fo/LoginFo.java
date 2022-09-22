package com.beneway.core.entity.sys_user.fo;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录表单实体类
 */
@Data
public class LoginFo implements Serializable {

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String verifyCode;


}
