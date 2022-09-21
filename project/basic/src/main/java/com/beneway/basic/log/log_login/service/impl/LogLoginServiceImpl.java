package com.beneway.basic.log.log_login.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.log.log_login.dao.LogLoginDao;
import com.beneway.basic.log.log_login.entity.po.LogLogin;
import com.beneway.basic.log.log_login.enums.LoginTypeEnum;
import com.beneway.basic.log.log_login.service.LogLoginService;
import com.beneway.basic.utils.AppUtils;
import com.beneway.basic.utils.IPUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 登录日志表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-22 15:21:16
 */
@Service("logLoginService")
public class LogLoginServiceImpl extends ServiceImpl<LogLoginDao, LogLogin> implements LogLoginService {

    @Resource
    private LogLoginDao logLoginDao;

    @Resource
    private AppUtils appUtils;

    /**
     * 添加登录日志记录
     * @param userId
     * @return
     */
    @Override
    public Integer addLoginLog(String userId, LoginTypeEnum loginType) {
        LogLogin logLogin = new LogLogin();
        logLogin.setUserId(userId);
        UserTypeEnum loginUserType = appUtils.getLoginUserType();
        logLogin.setUserType(loginUserType);
        logLogin.setLoginType(loginType);
        String moduleType = appUtils.getModuleType();
        logLogin.setModuleType(moduleType);
        String ipAddr = IPUtil.getIpAddr();
        logLogin.setLoginIp(ipAddr);
        logLogin.setCreateTime(new Date());

        this.save(logLogin);

        return logLogin.getId();
    }

}
