package com.beneway.basic.system.sys_filter_user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.basic.system.sys_filter_user.po.SysFilterUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户筛选配置
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-10 17:54:45
 */
@Mapper
@Repository
public interface SysFilterUserDao extends BaseMapper<SysFilterUser> {

}
