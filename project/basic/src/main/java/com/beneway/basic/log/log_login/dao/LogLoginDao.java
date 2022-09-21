package com.beneway.basic.log.log_login.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.log.log_login.entity.po.LogLogin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 登录日志表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-22 15:21:16
 */
@Mapper
@Repository
public interface LogLoginDao extends BaseMapper<LogLogin> {

}
