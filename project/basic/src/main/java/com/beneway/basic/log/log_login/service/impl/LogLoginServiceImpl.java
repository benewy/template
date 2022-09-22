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
