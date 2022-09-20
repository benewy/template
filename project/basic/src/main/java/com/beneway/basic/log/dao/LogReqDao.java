package com.beneway.basic.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.log.entity.logReq.LogReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogReqDao extends BaseMapper<LogReq> {

}
