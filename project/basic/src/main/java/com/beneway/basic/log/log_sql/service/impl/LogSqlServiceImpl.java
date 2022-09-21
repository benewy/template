package com.beneway.basic.log.log_sql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.log.log_sql.dao.LogSqlDao;
import com.beneway.basic.log.log_sql.entity.po.LogSql;
import com.beneway.basic.log.log_sql.service.LogSqlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * sql日志表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-21 09:43:53
 */
@Service("logSqlService")
public class LogSqlServiceImpl extends ServiceImpl<LogSqlDao, LogSql> implements LogSqlService {

    @Resource
    private LogSqlDao logSqlDao;

}
