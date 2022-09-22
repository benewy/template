package com.beneway.basic.system.sys_user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.system.sys_user.entity.po.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Create by zhy on 2022/3/2 9:58
 */
@Mapper
@Repository
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

}
