package com.beneway.basic.log.log_login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.basic.log.log_login.entity.po.LogLogin;
import com.beneway.basic.log.log_login.enums.LoginTypeEnum;

/**
 * 登录日志表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-22 15:21:16
 */
public interface LogLoginService extends IService<LogLogin> {

    /**
     * 添加登录日志记录
     * @param userId
     * @return
     */
    Integer addLoginLog(String userId, LoginTypeEnum loginType);

}

