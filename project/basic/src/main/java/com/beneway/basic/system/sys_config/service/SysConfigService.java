package com.beneway.basic.system.sys_config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.basic.system.sys_config.enums.SysConfigKeyEnum;
import com.beneway.basic.system.sys_config.po.SysConfig;

/**
 * 系统配置表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-18 10:16:22
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 获取配置信息
     * @param keyEnum
     */
    String getConfigValue(SysConfigKeyEnum keyEnum);

}

