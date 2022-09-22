package com.beneway.core.entity.sys_user.fo;

import com.beneway.basic.system.sys_user.entity.po.SysUser;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {

    public LoginVo(SysUser sysUser){
        this.setAccount(sysUser.getAccount());
        this.setUsername(sysUser.getUsername());
    }

    private String username;

    private String account;

    private String token;


}
