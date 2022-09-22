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

package com.beneway.basic.system.sys_message.modules.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.basic.system.sys_message.modules.MessageModuleService;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.beneway.basic.system.sys_user.service.SysUserService;
import com.beneway.basic.utils.dd.ZWDDPhoneUtils;
import com.beneway.basic.utils.dd.entity.msg.Card;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/27 17:42
 */
@Service("zwddPhoneMessageModuleService")
public class ZwddPhoneMessageModuleServiceImpl implements MessageModuleService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private ZWDDPhoneUtils zwddPhoneUtils;

    @Override
    public void sendMsg(String problemId, Collection<String> lookUserList, String content) {
        List<SysUser> sysUserList = sysUserService.list(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getZwddAccountId)
                .in(SysUser::getId, lookUserList)
                .isNotNull(SysUser::getZwddAccountId));
        List<String> zwddAccountIdList = sysUserList.stream().map(SysUser::getZwddAccountId).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(zwddAccountIdList)) {
            // 封装消息发送内容
            Card card = new Card();
            card.setMarkdown(content);
            zwddPhoneUtils.sendWorkCardMsg(zwddAccountIdList, card);
        }
    }

}
