package com.beneway.basic.system.sys_role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.system.sys_role.fo.SysRolePageQueryFo;
import com.beneway.basic.system.sys_role.po.SysRole;
import com.beneway.basic.system.sys_role.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-01 14:59:40
 */
@Mapper
@Repository
public interface SysRoleDao extends BaseMapper<SysRole> {

    Page<SysRoleVo> queryPage(Page page, @Param("params") SysRolePageQueryFo sysRolePageQueryFo);

}
