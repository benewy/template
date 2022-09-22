package com.beneway.basic.system.sys_message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.system.sys_message.fo.SysMessageQueryFo;
import com.beneway.basic.system.sys_message.po.SysMessage;
import com.beneway.basic.system.sys_message.vo.SysMessageVo;

import java.util.Collection;
import java.util.List;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-06 19:04:42
 */
public interface SysMessageService extends IService<SysMessage> {


    /**
     * 发送消息
     *
     * @param problemId
     * @param lookUser
     * @param content
     */
    void sendMsg(String problemId, String lookUser, String content);

    void sendMsg(String problemId, String lookUser, UserTypeEnum userType, String content);

    /**
     * 发送消息
     *
     * @param contractId
     * @param lookUserList
     * @param content
     */
    void sendMsg(String contractId, Collection<String> lookUserList, String content);

    void sendMsg(String contractId, Collection<String> lookUserList, UserTypeEnum userType, String content);

    /**
     * 获取当前用户信息列表，分页
     *
     * @param sysMessageQueryFo
     * @return
     */
    Page<SysMessageVo> queryPageMessage(SysMessageQueryFo sysMessageQueryFo);

    /**
     * 设置已读
     *
     * @param ids
     */
    void isRead(List<String> ids);

    /**
     * 删除已读信息
     *
     * @param userId
     */
    void clear(String userId);

    /**
     * 剩余未读信息数
     *
     * @param userId
     * @return
     */
    Integer messageUnread(String userId);

    /**
     * 全部已读
     * @param userId
     */
    void isReadAll(String userId);
}

