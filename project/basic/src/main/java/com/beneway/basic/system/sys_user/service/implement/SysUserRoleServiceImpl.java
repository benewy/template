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
