package com.beneway.basic.system.sys_message.modules.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.basic.system.sys_message.modules.MessageModuleService;
import com.beneway.basic.system.sys_user.entity.po.SysUser;
import com.beneway.basic.system.sys_user.service.SysUserService;
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
