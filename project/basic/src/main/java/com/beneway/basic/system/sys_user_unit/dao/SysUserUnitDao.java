package com.beneway.basic.system.sys_user_unit.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.system.sys_user_unit.entity.po.SysUserUnit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户单位关联表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-28 11:14:35
 */
@Mapper
@Repository
public interface SysUserUnitDao extends BaseMapper<SysUserUnit> {

}
