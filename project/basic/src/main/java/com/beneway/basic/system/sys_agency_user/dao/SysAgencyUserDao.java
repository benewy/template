package com.beneway.basic.system.sys_agency_user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.system.sys_agency_user.fo.SysAgencyUserQueryFo;
import com.beneway.basic.system.sys_agency_user.po.SysAgencyUser;
import com.beneway.basic.system.sys_agency_user.vo.SysAgencyUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysAgencyUserDao extends BaseMapper<SysAgencyUser> {

  Page<SysAgencyUserVo> queryPage(Page page, @Param("param") SysAgencyUserQueryFo sysAgencyUserQueryFo);
}
