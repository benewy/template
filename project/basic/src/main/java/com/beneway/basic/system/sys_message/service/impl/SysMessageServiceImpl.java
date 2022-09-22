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

package com.beneway.basic.system.sys_message.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.system.sys_message.dao.SysMessageDao;
import com.beneway.basic.system.sys_message.fo.SysMessageQueryFo;
import com.beneway.basic.system.sys_message.modules.MessageModuleUtils;
import com.beneway.basic.system.sys_message.po.SysMessage;
import com.beneway.basic.system.sys_message.service.SysMessageService;
import com.beneway.basic.system.sys_message.vo.SysMessageVo;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.beneway.basic.system.sys_user.service.SysUserService;
import com.beneway.basic.utils.dd.ZWDDPhoneUtils;
import com.beneway.basic.utils.dd.entity.msg.Card;
import com.beneway.basic.utils.page.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-06 19:04:42
 */
@Service("sysMessageService")
public class SysMessageServiceImpl extends ServiceImpl<SysMessageDao, SysMessage> implements SysMessageService {

    @Resource
    private SysMessageDao sysMessageDao;

    @Resource
    private MessageModuleUtils messageModuleUtils;

    @Resource
    private ZWDDPhoneUtils zwddPhoneUtils;

    @Resource
    private SysUserService sysUserService;

    /**
     * 发送消息
     *
     * @param problemId
     * @param lookUser
     * @param content
     */
    @Override
    public void sendMsg(String problemId, String lookUser, String content) {
        this.sendMsg(problemId, lookUser, UserTypeEnum.SYSTEM, content);
    }

    @Override
    public void sendMsg(String problemId, String lookUser, UserTypeEnum userType, String content) {
        if (StringUtils.isNoneBlank(lookUser)){
            List<String> lookUserList = Arrays.asList(lookUser.split(","));
            sendMsg(problemId, lookUserList, userType, content);
        }
    }

    /**
     * 发送消息
     *
     * @param contractId
     * @param lookUserList
     * @param content
     */
    @Override
    public void sendMsg(String contractId, Collection<String> lookUserList, String content) {
        sendMsg(contractId, lookUserList, UserTypeEnum.SYSTEM, content);
        System.out.println();
    }

    @Override
    public void sendMsg(String problemId, Collection<String> lookUserList, UserTypeEnum userType, String content) {
        if (CollUtil.isNotEmpty(lookUserList)) {
            Set<String> lookUserSet = lookUserList.stream().collect(Collectors.toSet());
            List<SysMessage> sysMessageList = new ArrayList<>(lookUserSet.size());
            Date createTime = new Date();
            for (String lookUser : lookUserSet) {
                SysMessage sysMessage = new SysMessage();
                sysMessage.setProblemId(problemId);
                sysMessage.setLookUser(lookUser);
                sysMessage.setUserType(userType);
                sysMessage.setContent(content);
                sysMessage.setCreateTime(createTime);
                sysMessageList.add(sysMessage);
            }
            this.saveBatch(sysMessageList);

            // 发送模块消息
            messageModuleUtils.sendMsg(problemId, lookUserList, userType, content);

            //获取用户
            List<SysUser> users = sysUserService.listByIds(lookUserList);
            List<String> zwddAccountIdList = users.stream()
                    .map(SysUser::getZwddAccountId)
                    .collect(Collectors.toList());

            //发送工作通知
            Card card = new Card();
            card.setMarkdown(content);
            zwddPhoneUtils.sendWorkCardMsg(zwddAccountIdList, card);
        }
    }

    /**
     * 获取当前用户信息列表，分页
     *
     * @param sysMessageQueryFo
     * @return
     */
    @Override
    public Page<SysMessageVo> queryPageMessage(SysMessageQueryFo sysMessageQueryFo) {
        Page page = PageUtils.getPage(sysMessageQueryFo);
        Page<SysMessageVo> iPage = sysMessageDao.queryPageMessage(page, sysMessageQueryFo);
        return iPage;
    }

    /**
     * 设置已读
     *
     * @param ids
     */
    @Override
    public void isRead(List<String> ids) {
        for (String id : ids) {
            SysMessage sysMessage = new SysMessage();
            sysMessage.setId(id)
                    .setLookTime(new Date());
            updateById(sysMessage);
        }
    }

    /**
     * 删除已读信息
     *
     * @param userId
     */
    @Override
    public void clear(String userId) {
        remove(new LambdaQueryWrapper<SysMessage>()
                .eq(SysMessage::getLookUser, userId)
                .isNotNull(SysMessage::getLookTime));
    }

    /**
     * 剩余未读信息数
     *
     * @param userId
     * @return
     */
    @Override
    public Integer messageUnread(String userId) {
        return Math.toIntExact(this.count(new LambdaQueryWrapper<SysMessage>()
          .eq(SysMessage::getLookUser, userId)
          .isNull(SysMessage::getLookTime)));
    }

    /**
     * 全部已读
     */
    @Override
    public void isReadAll(String userId) {
        update(new LambdaUpdateWrapper<SysMessage>()
                .set(SysMessage::getLookTime, new Date())
                .eq(SysMessage::getLookUser, userId)
                .isNull(SysMessage::getLookTime));
    }

}
