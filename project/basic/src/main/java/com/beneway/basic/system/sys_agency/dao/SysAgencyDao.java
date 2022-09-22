package com.beneway.basic.system.sys_agency.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.system.sys_agency.fo.SysAgencyQueryFo;
import com.beneway.basic.system.sys_agency.po.SysAgency;
import com.beneway.basic.system.sys_agency.vo.SysAgencyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-05-30 15:30:50
 */
@Mapper
@Repository
public interface SysAgencyDao extends BaseMapper<SysAgency> {

    Page<SysAgencyVo> queryPage(Page page, @Param("param") SysAgencyQueryFo sysAgencyQueryFo);


}
