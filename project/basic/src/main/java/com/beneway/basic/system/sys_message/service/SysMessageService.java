/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

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

