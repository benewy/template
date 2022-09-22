package com.beneway.basic.system.sys_filter_unit.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.system.sys_filter_unit.po.SysFilterUnit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户筛选配置
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-10 20:14:11
 */
@Mapper
@Repository
public interface SysFilterUnitDao extends BaseMapper<SysFilterUnit> {

}
