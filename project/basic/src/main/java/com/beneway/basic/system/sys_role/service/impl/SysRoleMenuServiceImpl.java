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

package com.beneway.basic.system.sys_role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beneway.basic.system.sys_role.dao.SysRoleMenuDao;
import com.beneway.basic.system.sys_role.po.SysRoleMenu;
import com.beneway.basic.system.sys_role.service.SysRoleMenuService;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-01 14:59:39
 */
@EnableAspectJAutoProxy( proxyTargetClass = true , exposeProxy = true )
@CacheConfig(cacheNames = "sysRoleMenu")
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    private SysRoleMenuService getCurrThis(){
        SysRoleMenuService currentProxy = (SysRoleMenuService) AopContext.currentProxy();
        return currentProxy;
    }

    @Cacheable(key = "'list[' + #sysRoleId + ']'")
    @Override
    public List<Integer> getMenuIdList(Integer sysRoleId) {
        List<SysRoleMenu> list = this.list(new LambdaQueryWrapper<SysRoleMenu>()
                .select(SysRoleMenu::getSysMenuId)
                .eq(SysRoleMenu::getSysRoleId, sysRoleId));
        List<Integer> sysMenuIdList = list.stream().map(SysRoleMenu::getSysMenuId).collect(Collectors.toList());
        return sysMenuIdList;
    }

    @CacheEvict(key = "'list[' + #sysRoleId + ']'")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addRoleMenu(Integer sysRoleId, List<Integer> sysMenuIdList) {
        List<SysRoleMenu> list = new LinkedList<>();
        for (Integer sysMenuId : sysMenuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setSysRoleId(sysRoleId);
            sysRoleMenu.setSysMenuId(sysMenuId);
            list.add(sysRoleMenu);
        }
        this.saveBatch(list);
    }

    @CacheEvict(key = "'list[' + #sysRoleId + ']'")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRoleMenu(Integer sysRoleId, List<Integer> sysMenuIdList) {
        removeRoleMenu(sysRoleId);
        addRoleMenu(sysRoleId, sysMenuIdList);
    }

    @CacheEvict(key = "'list[' + #sysRoleId + ']'")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeRoleMenu(Integer sysRoleId) {
        this.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getSysRoleId, sysRoleId));
    }
}
