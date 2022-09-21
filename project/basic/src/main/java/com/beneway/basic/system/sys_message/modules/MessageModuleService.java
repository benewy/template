package com.beneway.basic.system.sys_message.modules;

import java.util.Collection;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/27 17:29
 *
 * 发送消息模块接口
 */
public interface MessageModuleService {

    /**
     * 发送消息
     * @param problemId
     * @param lookUserList
     * @param content
     */
    void sendMsg(String problemId, Collection<String> lookUserList, String content);

}
