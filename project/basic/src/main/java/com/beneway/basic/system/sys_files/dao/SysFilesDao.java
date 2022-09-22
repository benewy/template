package com.beneway.basic.system.sys_files.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.system.sys_files.po.SysFiles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysFilesDao extends BaseMapper<SysFiles> {

    @Select("SELECT * FROM files WHERE uuid = #{id};")
    SysFiles find(@Param("id") String id);

}
