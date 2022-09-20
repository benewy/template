package com.beneway.basic.system.sys_area.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.system.sys_area.po.SysArea;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysAreaDao extends BaseMapper<SysArea> {
}
