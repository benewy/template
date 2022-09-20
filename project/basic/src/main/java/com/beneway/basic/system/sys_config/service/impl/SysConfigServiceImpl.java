package com.beneway.basic.system.sys_config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_config.dao.SysConfigDao;
import com.beneway.basic.system.sys_config.enums.SysConfigKeyEnum;
import com.beneway.basic.system.sys_config.po.SysConfig;
import com.beneway.basic.system.sys_config.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统配置表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-18 10:16:22
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {

    @Resource
    private SysConfigDao sysConfigDao;

    /**
     * 获取配置信息
     * @param keyEnum
     */
    @Override
    public String getConfigValue(SysConfigKeyEnum keyEnum) {
        SysConfig config = this.getOne(new LambdaQueryWrapper<SysConfig>()
                .eq(SysConfig::getKey, keyEnum.getKey()));
        return config.getValue();
    }

}
