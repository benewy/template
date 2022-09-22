package com.beneway.basic.system.sys_message.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.system.sys_message.fo.SysMessageQueryFo;
import com.beneway.basic.system.sys_message.po.SysMessage;
import com.beneway.basic.system.sys_message.vo.SysMessageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-06 19:04:42
 */
@Mapper
@Repository
public interface SysMessageDao extends BaseMapper<SysMessage> {
    /**
     * 获取消息，分页
     * @author  yhz
     * @create  2022/4/11 17:02
     * @Param
     * @return
     **/
    Page<SysMessageVo> queryPageMessage(Page page, @Param("param") SysMessageQueryFo sysMessageQueryFo);
}
