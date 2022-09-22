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

package com.beneway.basic.system.sys_user.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_user.dao.SysUserRoleDao;
import com.beneway.basic.system.sys_user.entity.po.SysUserRole;
import com.beneway.basic.system.sys_user.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Create by zhy on 2022/3/2 10:02
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserRole(String userId, List<Integer> roleIdList) {
        List<SysUserRole> list = new LinkedList<>();
        for (Integer roleId : roleIdList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setSysUserId(userId);
            sysUserRole.setSysRoleId(roleId);
            list.add(sysUserRole);
        }
        this.saveBatch(list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserRole(String userId, List<Integer> roleIdList) {
        removeUserRole(userId);
        addUserRole(userId, roleIdList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeUserRole(String userId) {
        this.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getSysUserId, userId));
    }

}
