package com.beneway.basic.system.sys_config.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.system.sys_config.po.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统配置表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-18 10:16:22
 */
@Mapper
@Repository
public interface SysConfigDao extends BaseMapper<SysConfig> {

}
