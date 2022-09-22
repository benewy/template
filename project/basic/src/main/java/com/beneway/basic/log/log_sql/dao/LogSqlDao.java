package com.beneway.basic.log.log_sql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.log.log_sql.entity.po.LogSql;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * sql日志表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-21 09:43:53
 */
@Mapper
@Repository
public interface LogSqlDao extends BaseMapper<LogSql> {

}
